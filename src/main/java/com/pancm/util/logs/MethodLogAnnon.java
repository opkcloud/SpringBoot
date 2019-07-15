package com.pancm.util.logs;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface MethodLogAnnon {

    /**
     * 模块
     * @return
     */
    String model() default "";

    /**
     * 操作类型
     * @return
     */
//    LogTypeEnum type() default LogTypeEnum.QUERY;

    /**
     * 方法名称
     * @return
     */
    String value() default "";

}
