package com.opkcloud.distributedlock.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redisson")
@Getter
@Setter
public class RedissonProperties {

    private int timeout = 3000;

    private String address;

    private String password;

    private int database = 7;

    private int connectionPoolSize = 64;

    private int connectionMinimumIdleSize = 10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddresses;

    private String masterName;

}
