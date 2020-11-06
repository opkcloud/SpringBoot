package com.opkcloud.service.impl;

import com.opkcloud.log.OperateLoggerMapper;
import com.opkcloud.log.OperateLogger;
import com.opkcloud.service.OperateLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service("OperateLoggerService")
public class OperateLoggerServiceImpl implements OperateLoggerService {

    @Autowired
    private OperateLoggerMapper loggerMapper;

    @Override
    public void addOperateLogger(OperateLogger logger) {
        try {
            String id = UUID.randomUUID().toString();
            logger.setNumId(id);
            loggerMapper.insertSelective(logger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
