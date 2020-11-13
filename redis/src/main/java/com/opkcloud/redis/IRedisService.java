package com.opkcloud.redis;

public interface IRedisService {

    /**
     * 存入key
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);

}
