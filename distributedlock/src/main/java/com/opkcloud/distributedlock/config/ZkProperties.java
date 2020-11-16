package com.opkcloud.distributedlock.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "curator")
public class ZkProperties {

    /**
     * 重试次数
     */
    private int retryCount;

    /**
     * 重试间隔时间
     */
    private int elapsedTimeMs;

    /**
     * zookeeper 地址，127.0.0.1:2181
     */
    private String connectString;

    private int sessionTimeoutMs;

    private int connectionTimeoutMs;

}
