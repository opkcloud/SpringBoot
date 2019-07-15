package com.pancm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDistributionLock {

    private static final long LOCK_TIMEOUT = 50 * 60 * 1000;
    private static final Logger logger = LoggerFactory.getLogger(RedisDistributionLock.class);

    private static RedisTemplate<Serializable, Serializable> redisTemplate = null;

    public RedisDistributionLock() {}

    public RedisDistributionLock(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long lock(final String lockKey, String threadName) {
        final Long lock_timeout = System.currentTimeMillis() + LOCK_TIMEOUT + 1;
        if (redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
                byte[] value = lock_timeout.toString().getBytes();
                return connection.setNX(lockKey.getBytes(), value);
            }
        })) {
            logger.info(threadName + "");
//            redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
            redisTemplate.boundValueOps(lockKey).set(lock_timeout, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
            return lock_timeout;
        }
        return null;
    }

    public void unLock(String lockKey, long lockValue, String threadName) {
//        Serializable serializable = redisTemplate.boundValueOps(lockKey).get();
        Long currt_lock_timeout_str = (Long) redisTemplate.opsForValue().get(lockKey);
        if (currt_lock_timeout_str != null && currt_lock_timeout_str == lockValue) {
            redisTemplate.delete(lockKey);
            logger.info(threadName + "");
        }
    }
}
