package com.opkcloud.distributedlock.service.impl;

import com.opkcloud.distributedlock.config.ZkConnectionStateListener;
import com.opkcloud.distributedlock.config.ZkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 分布式互斥（不可重入锁）：即同一进程中的同一个线程不可多次获取某个指定的分布式锁
 */

@Slf4j
public abstract class AbstractZkDistributedLock {

    private static ZkProperties zkProperties;
    protected static CuratorFramework zkClient;
    protected static InterProcessLock LOCK;
    protected static String basePath;

    public AbstractZkDistributedLock(String basePath, ZkProperties zkProperties) {
        AbstractZkDistributedLock.zkProperties = zkProperties;
        AbstractZkDistributedLock.basePath = basePath.contains("/") ? basePath : ("/" + basePath);
    }

    protected void initZkClient() {
        if (zkClient == null && zkClient.getState() != CuratorFrameworkState.STARTED) {
            zkClient = CuratorFrameworkFactory.builder()
                    .connectString(AbstractZkDistributedLock.zkProperties.getConnectString())
                    .sessionTimeoutMs(AbstractZkDistributedLock.zkProperties.getSessionTimeoutMs())
                    .connectionTimeoutMs(AbstractZkDistributedLock.zkProperties.getConnectionTimeoutMs())
                    // ExponentialBackoffRetry 重连的时间间隔随着重试的次数递增的，类似支付宝支付回调策略
                    .retryPolicy(new ExponentialBackoffRetry(AbstractZkDistributedLock.zkProperties.getElapsedTimeMs(), AbstractZkDistributedLock.zkProperties.getRetryCount()))
                    .build();
            // ZkConnectionStateListener 断线重连注册锁信息
//            ZkConnectionStateListener zkConnectionStateListener = new ZkConnectionStateListener(AbstractZkDistributedLock.basePath);
//            zkClient.getConnectionStateListenable().addListener(zkConnectionStateListener);
            zkClient.start();
        }
    }

}
