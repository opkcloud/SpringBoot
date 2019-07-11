package com.pancm.dao;
import java.util.List;

import com.pancm.pojo.OperateLogger;
import org.apache.ibatis.annotations.Param;

public interface OperateLoggerMapper {

    int insert(OperateLogger record);

    int insertSelective(OperateLogger record);

}