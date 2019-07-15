package com.pancm.dao;

import com.pancm.pojo.OperateLogger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLoggerMapper {

    int insert(OperateLogger record);

    int insertSelective(OperateLogger record);

}