package com.opkcloud.redis;

public interface IRedisService {

    /**
     * 存入key
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);

    /**
     * 删除缓存
     * 根据 key 精确匹配删除
     * @param key
     */
    void del(String... key);

}
