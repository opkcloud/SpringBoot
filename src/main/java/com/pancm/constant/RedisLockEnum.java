package com.pancm.constant;

public enum  RedisLockEnum {

    findAll("findAll", "查询所有数据");

    String name;
    String description;

    RedisLockEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
