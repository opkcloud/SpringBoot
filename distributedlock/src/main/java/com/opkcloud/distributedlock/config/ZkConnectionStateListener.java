package com.opkcloud.distributedlock.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ZkConnectionStateListener implements ConnectionStateListener {

    private String regPath;
    private String regData;

    /**
     * 默认为临时顺序节点
     */
    private CreateMode createMode = CreateMode.EPHEMERAL_SEQUENTIAL;

    public ZkConnectionStateListener(String regPath) {
        this.regPath = regPath;
    }

    public ZkConnectionStateListener(String regPath, CreateMode createMode) {
        this.regPath = regPath;
        this.createMode = createMode == null ? CreateMode.EPHEMERAL_SEQUENTIAL : createMode;
    }

    public ZkConnectionStateListener(String regPath, String regData) {
        this.regPath = regPath;
        this.regData = regData;
    }

    public ZkConnectionStateListener(String regPath, String regData, CreateMode createMode) {
        this.regPath = regPath;
        this.regData = regData;
        this.createMode = createMode == null ? CreateMode.EPHEMERAL_SEQUENTIAL : createMode;
    }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
        if (connectionState == ConnectionState.LOST) {
            while (true) {
                try {
                    Thread.sleep(2000);
                    log.warn("zk 连接丢失，开始重连... {}", this.toString());
                    if (curatorFramework.getZookeeperClient().blockUntilConnectedOrTimedOut()) {
                        if (regData != null) {
                            curatorFramework.create().creatingParentsIfNeeded().withMode(createMode)
                                    .forPath(regPath, regData.getBytes(StandardCharsets.UTF_8));
                        } else {
                            curatorFramework.create().creatingParentsIfNeeded().withMode(createMode).forPath(regPath);
                        }
                        log.info("zk 断线重连后重新注册节点信息完成，{}", this.toString());
                        break;
                    }
                } catch (InterruptedException e) {
                    log.warn("zookeeper reconnect take error, {}", this.toString(), e);
                    break;
                } catch (Exception e) {
                    log.warn("zookeeper reconnect take error, {}", this.toString(), e);
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.format("regPath = %s, regData = %s, createMode = %s", regPath, regData, createMode.toString());
    }
}
