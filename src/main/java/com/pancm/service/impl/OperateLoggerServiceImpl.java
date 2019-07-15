package com.pancm.service.impl;

import com.pancm.dao.OperateLoggerMapper;
import com.pancm.pojo.OperateLogger;
import com.pancm.service.OperateLoggerService;
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
