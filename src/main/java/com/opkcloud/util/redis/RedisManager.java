//package com.opkcloud.util.redis;
//
//import org.springframework.beans.factory.annotation.Value;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//public class RedisManager {
//
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private int port;
//    @Value("${redis.password}")
//    private String password;
//    @Value("${redis.expire}")
//    private int expire = 0;
//
//    private static JedisPool jedisPool;
//
//    public RedisManager() {
//    }
//
//    public static JedisPool getJedisPool() {
//        if (null == jedisPool) {
//            new RedisManager().init();
//        }
//        return jedisPool;
//    }
//
//    public void init() {
//        if (null == host || 0 == port) {
//            System.out.println("请初始化redis配置文件");
//            throw new NullPointerException("找不到redis配置");
//        }
//        if (jedisPool == null) {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxTotal(1000);
//            config.setMaxIdle(100);
//            config.setMaxWaitMillis(10000);
//            jedisPool = new JedisPool(config, host, port, expire, password);
//        }
//    }
//}
