package com.pancm.constant;

public enum UserEmun {

    USER_KEY("USER", "存在Session中的用户KEY"),
    RATION_KEY("RATION", "存在Session中的用户权限KEY");

    private String code;
    private String desc;

    UserEmun(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
