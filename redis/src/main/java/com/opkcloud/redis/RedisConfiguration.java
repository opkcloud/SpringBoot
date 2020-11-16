package com.opkcloud.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        // 设置自动 key 的生成规则，配置 springboot 注解，进行方法级别的缓存
        // 使用：进行分割，可以很好地显示层级关系
        // 这里 new 了一个 keyGenerator 对象，用了 lambda 表达式的写法
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":" + String.valueOf(obj));
            }
            String reToUse = String.valueOf(sb);
            log.info("自动生成 Redis key -> [{}]", reToUse);
            return reToUse;
        };
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        // 初始化缓存管理器
        log.info("初始化 -> [{}]", "CacheManager RedisCacheManager start");
        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory);
        return builder.build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        // 配置 redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        RedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);         // key 序列化
        redisTemplate.setValueSerializer(serializer);       // value 序列化
        redisTemplate.setHashKeySerializer(serializer);     // hash key 序列化
        redisTemplate.setHashValueSerializer(serializer);   // hash value 序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 异常处理，redis 出现异常，打印日志，程序正常走
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError: key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCacheGetError: key -> [{}], value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError: key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheGetError: key -> [{}]", e);
            }
        };
        return cacheErrorHandler;
    }

    /**
     * 创建 JedisConnectionFactory 和 JdeisPool，以供外部类初始化缓存管理器使用
     */
    @ConfigurationProperties
    class DataJedisProperties {

        @Value("${redis.host:127.0.0.1}")
        private String host;

        @Value("${redis.password:}")
        private String password;

        @Value("${redis.port:6379}")
        private int port;

        @Value("${redis.timeout:10000}")
        private int timeout;

        @Value("${redis.database:0}")
        private int database;

        @Value("${redis.jedis.pool.max-idle:5}")
        private int maxIdle;

        @Value("${redis.jedis.pool.max-wait:5000}")
        private long maxWaitMillis;

        @Value("${redis.jedis.pool.max-active:20}")
        private int maxActive;

        @Value("${redis.jedis.pool.testOnBorrow:true}")
        private boolean testOnBorrow;

        @Value("${redis.sentinel.nodes:}")
        private String redisNodes;

        @Value("${redis.sentinel.master}")
        private String master;

        @Bean
        public RedisSentinelConfiguration redisSentinelConfiguration() {
            RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
            if (StringUtils.isEmpty(redisNodes)) {
                return configuration;
            }
            if (StringUtils.isEmpty(master)) {
                return configuration;
            }
            String[] hosts = redisNodes.split(",");
            for (String redisHost : hosts) {
                String[] item = redisHost.split(":");
                String ip = item[0];
                String port = item[1];
                configuration.addSentinel(new RedisNode(ip, Integer.valueOf(port)));
            }
            configuration.setMaster(master);
            return configuration;
        }

        @Autowired
        private RedisSentinelConfiguration redisSentinelConfiguration;

        JedisConnectionFactory jedisConnectionFactory() {
            log.info("JedisPool init successful, host -> [{}]: port -> [{}]", host, port);
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            jedisPoolConfig.setMaxTotal(maxActive);
            jedisPoolConfig.setTestOnBorrow(testOnBorrow);

            JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
            if (!StringUtils.isEmpty(redisNodes) && !StringUtils.isEmpty(master)) {
                factory = new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolConfig);
            }
            factory.setHostName(host);
            factory.setPort(port);
            factory.setTimeout(timeout);
            factory.setPassword(password);
            factory.setDatabase(database);

            return factory;
        }
    }
}
