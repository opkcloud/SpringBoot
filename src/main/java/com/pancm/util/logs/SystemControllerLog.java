package com.pancm.util.logs;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.METHOD})
public @interface SystemControllerLog {

    // 操作类型（0：登录；1：查询；2：新增；3：修改；4：删除；5：导出）
    String description() default "";

    String operateName() default "";

    String operateNape() default "";

}
