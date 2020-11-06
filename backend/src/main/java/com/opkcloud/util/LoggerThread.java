package com.opkcloud.util;

import com.opkcloud.log.service.OperateLoggerService;
import com.opkcloud.log.bean.OperateLogger;
import com.opkcloud.user.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(LoggerThread.class);

    private OperateLogger entity;
    private User user;

    public LoggerThread(OperateLogger entity, User user) {
        this.entity = entity;
        this.user = user;
    }

    @Override
    public void run() {
        entity.setUserId("");
        entity.setUserName("");
        entity.setOrganization("");
        entity.setOrganizationId("");

        String format = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        entity.setOperateTime(sdf.format(new Date()));
        entity.setOperateNumber(0);
        entity.setOperateTable("");
        entity.setOperateKey("");

        logger.info("===== 记录操作日志开始 =====");
        try {
            OperateLoggerService operateLoggerService = SpringUtil.getBean("OperateLoggerService");
            operateLoggerService.addOperateLogger(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加操作日志失败", e);
        }
        logger.info("===== 记录操作日志结束 =====");
    }
}
