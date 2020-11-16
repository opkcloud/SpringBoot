package com.opkcloud.distributedlock.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.opkcloud.distributedlock.service.IDistributedReentrantLock;
import com.opkcloud.redis.IRedisService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * 分布式互斥可重入锁：即同一进程中的同一个线程可多次获取某个指定的分布式锁
 */
@Slf4j
@Service
@Primary
public class RedissonDistributedReentrantLock implements IDistributedReentrantLock {

    @Autowired
    private RedissonClient redissonClient;

    private static final ConcurrentMap<Thread, LockData> THREAD_LOCAL = Maps.newConcurrentMap();

    @Autowired
    private IRedisService redisService;
    private static IRedisService redis;

    @PostConstruct
    public void init() {
        RedissonDistributedReentrantLock.redis = redisService;
    }

    /**
     * 若程序异常退出则及时清除当前进程存在存在的分布式锁
     * redisson 分布式锁默认过期时间为 30s，等到 TTL 为 20s 时会重新自动续约（renew）至 30s
     * 为了防止程序异常退出时仍长时间持有锁从而导致其他进程无法及时获取到锁
     * 所以此处需要增加程序异常退出时自动解锁功能
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (THREAD_LOCAL.size() == 0) {
                return;
            }

            log.info("Program abnormal exit, remove all redisson distributed lock ...");
            for (LockData item : THREAD_LOCAL.values()) {
                RLock lock = item.getLock();
                if (lock != null && lock.isLocked()) {
                    log.info("Start removing redisson distributed lock : {}, cause program abnormal exit, ", item.getKey());
                    try {
                        // 此处不能直接调用 lock.unlock 方法解锁，因为当前线程不属于加锁时所在的线程
                        // 故此处调用 unlock 会提示 IllegalMonitorStateException 异常
                        redis.del(item.getKey());
                        log.debug("Removing redisson distributed lock : {} successful, cause program abnormal exit. ", item.getKey());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        log.warn("Removing redisson distributed lock : {} take error: {}, cause program abnormal exit. ", item.getKey(), ex.getMessage());
                    }
                }
            }
        }));
    }

    @Override
    public void acquire(String key) {
        try {
            key = TAG.replace("/", "") + "_" + key;
            log.info("acquire redisson distributed lock : {}", key);

            RLock lock = redissonClient.getLock(key);
            lock.lock();

            Thread currentThread = Thread.currentThread();
            LockData data = THREAD_LOCAL.get(currentThread);
            if (data == null) {
                data = new LockData(lock, key);
                THREAD_LOCAL.put(currentThread, data);
            } else {
                data.getLongAdder().increment();
            }
            log.info("acquire redisson distributed lock {} successful, lockCount : {}", key, data.getLongAdder().doubleValue());
        } catch (Exception ex) {
            log.error("redisson require distributed reentrant lock take error: {}", key, ex);
        }
    }

    @Override
    public boolean release() {
        Thread currentThread = Thread.currentThread();
        LockData lockData = THREAD_LOCAL.get(currentThread);
        try {
            if (lockData == null) {
                throw new IllegalMonitorStateException("You do not own the redisson lock. ");
            }
            if (lockData.getLongAdder().doubleValue() > 0) {
                lockData.getLongAdder().decrement();
            }

            if (lockData.getLongAdder().doubleValue() < 0) {
                throw new IllegalMonitorStateException("Lock count has gone negative for redisson lock. ");
            }

            log.info("release redisson distributed lock {} successful, lockCount: {} . ", lockData.getKey(), lockData.getLongAdder().doubleValue());

            if (lockData.getLongAdder().doubleValue() == 0) {
                RLock lock = lockData.getLock();
                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                    THREAD_LOCAL.remove(currentThread);
                }
            }

            return true;
        } catch (IllegalMonitorStateException ex) {
            log.error("redisson release distributed reentrant lock take error: {}", JSON.toJSONString(lockData), ex);
        }
        return false;
    }

    @Getter
    @Setter
    private static class LockData {
        private RLock lock;
        private LongAdder longAdder;
        private String key;

        public LockData(RLock lock, String key) {
            this.lock = lock;
            this.key = key;
            this.longAdder = longAdder;
            this.longAdder.increment();
        }
    }

}
