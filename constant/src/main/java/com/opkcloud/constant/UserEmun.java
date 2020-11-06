package com.opkcloud.constant;

import lombok.Getter;

@Getter
public enum UserEmun {

    USER_KEY("USER", "存在Session中的用户KEY"),
    RATION_KEY("RATION", "存在Session中的用户权限KEY");

    private String code;
    private String desc;

    UserEmun(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
