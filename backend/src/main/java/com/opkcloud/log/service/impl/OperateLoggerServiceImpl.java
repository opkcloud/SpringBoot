package com.opkcloud.log.service.impl;

import com.opkcloud.log.bean.OperateLogger;
import com.opkcloud.log.dao.OperateLoggerMapper;
import com.opkcloud.log.service.OperateLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
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
