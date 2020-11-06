package com.opkcloud.constant;

import lombok.Getter;

@Getter
public enum RedisLockEnum {

    FINDALL("findAll", "查询所有数据");

    String name;
    String description;

    RedisLockEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
