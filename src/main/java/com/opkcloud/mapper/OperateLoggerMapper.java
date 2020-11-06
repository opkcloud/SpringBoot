package com.opkcloud.mapper;

import com.opkcloud.pojo.OperateLogger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLoggerMapper {

    int insert(OperateLogger record);

    int insertSelective(OperateLogger record);

}