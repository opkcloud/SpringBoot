package com.opkcloud.distributedlock.service.impl;

import com.opkcloud.distributedlock.config.ZkProperties;
import com.opkcloud.distributedlock.service.IDistributedExclusiveLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.springframework.stereotype.Service;

/**
 * 分布式互斥（不可重入锁）：即同一进程中的同一个线程不可多次获取某个指定的分布式锁
 */
@Slf4j
@Service
public class ZkDistributedExclusiveLock extends AbstractZkDistributedLock implements IDistributedExclusiveLock {

    public ZkDistributedExclusiveLock(String basePath, ZkProperties zkProperties) {
        super(TAG, zkProperties);
    }

    @Override
    public void acquire(String path) {
        try {
            initZkClient();
            if (LOCK == null) {
                path = path.startsWith("/") ? path : ("/" + path);
                path = TAG + path;
                LOCK = new InterProcessSemaphoreMutex(zkClient, path);
            }
            LOCK.acquire();
        } catch (Exception ex) {
            log.error("zk require exclusive lock take error", ex);
        }
    }

    @Override
    public boolean release() {
        try {
            if (LOCK != null) {
                LOCK.release();
            }
            return true;
        } catch (Exception ex) {
            log.error("zk release exclusive lock take error", ex);
        }
        return false;
    }
}
