package com.opkcloud.distributedlock.service.impl;

import com.opkcloud.distributedlock.config.ZkProperties;
import com.opkcloud.distributedlock.service.IDistributedReentrantLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.stereotype.Service;

/**
 *
 */
@Slf4j
@Service
public class ZkDistributedReentrantLock extends AbstractZkDistributedLock implements IDistributedReentrantLock {

    public ZkDistributedReentrantLock(String basePath, ZkProperties zkProperties) {
        super(TAG, zkProperties);
    }

    @Override
    public void acquire(String path) {
        try {
            initZkClient();
            if (LOCK == null) {
                path = path.startsWith("/") ? path : ("/" + path);
                path = TAG + path;
                LOCK = new InterProcessMutex(zkClient, path);
            }
            LOCK.acquire();
        } catch (Exception ex) {
            log.error("zk require distributed reentrant lock take error", ex);
        }
    }

    @Override
    public boolean release() {
        try {
            if (LOCK != null) {
                LOCK.release();
            }
            return true;
        } catch (Exception ex) {
            log.error("zk release distributed reentrant lock take error", ex);
        }
        return false;
    }
}
