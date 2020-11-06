package com.opkcloud.log.dao;

import com.opkcloud.log.bean.OperateLogger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLoggerMapper {

    int insert(OperateLogger record);

    int insertSelective(OperateLogger record);

}