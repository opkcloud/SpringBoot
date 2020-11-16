package com.opkcloud.distributedlock.service;

public interface IDistributedLock {

    /**
     * 获取分布式锁
     * @param path
     */
    void acquire(String path);

    /**
     * 释放分布式锁
     * @return
     */
    boolean release();

}
