package com.opkcloud.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedisService implements IRedisService {

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    private long defaultExpireTime;

    @Value("${default.cache-expire: 30m}")
    public void setDefaultExpireTime(String defaultExpireTimeStr) {
        this.defaultExpireTime = getExpireTimeByTimeStr(defaultExpireTimeStr);
    }

    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 60 * ONE_MINUTE;
    private static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * \d+(\.\d+)? 匹配正数或小数
     * TIME_PATTERN 正则表达式过长，由于不是检测重复出现的单词故不可使用反向引用
     */
    private static final Pattern TIME_PATTERN = Pattern.compile(
            "((?<day>\\d+(\\.\\d+)?)(?<dayUnit>d))?" +          // 匹配天数 day
            "((?<hour>\\d+(\\.\\d+)?)(?<hourUnit>h))?" +        // 匹配小时 hour
            "((?<minute>\\d+(\\.\\d+)?)(?<minuteUnit>m))?" +    // 匹配分钟 minute
            "((?<second>\\d+(\\.\\d+)?)(?<secondUnit>s?))?"     // 匹配秒数 second
    );

    private static final HashMap<String,Long> TIME_UNIT_CHCHE = new HashMap<String,Long>(){
        {
            put("d", ONE_DAY);
            put("h", ONE_HOUR);
            put("m", ONE_MINUTE);
            put("s", 1L);
        }
    };

    private long getExpireTimeByTimeStr(String time) {
        Matcher matcher = TIME_PATTERN.matcher(time);
        if (!matcher.matches()) {
            throw new RuntimeException("redis parse expire time take error: " + time);
        }

        // parse day
        long expireTime = parseTime(matcher, "day");
        // parse hour
        expireTime += parseTime(matcher, "hour");
        // parse minute
        expireTime += parseTime(matcher, "minute");
        // parse second
        expireTime += parseTime(matcher, "second");

        return expireTime;
    }

    private long parseTime(Matcher matcher, String digitGroup) {
        long expireTime = 0;
        String digit = matcher.group(digitGroup);
        String unit = matcher.group(digitGroup + "Unit");
        if (!StringUtils.isEmpty(digit)) {
            if (StringUtils.isEmpty(unit)) {
                unit = "s";
            }
            if (!TIME_UNIT_CHCHE.containsKey(unit)) {
                throw new RuntimeException("redis expire time syntax not support unit: " + unit);
            }
            expireTime += TIME_UNIT_CHCHE.get(unit) * Double.parseDouble(digit);
        }
        return expireTime;
    }

    /**
     * 存入 key
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean set(String key, String value) {
        return execute(((connection, serializer) -> {
            return connection.set(serializer.serialize(key), serializer.serialize(value));
        }));
    }

    @Override
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    private <T> T execute(final BiFunction<RedisConnection, RedisSerializer<String>, T> callback) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        return redisTemplate.execute((RedisCallback<T>)  connection -> {
            return callback.apply(connection, serializer);
        });
    }
}
