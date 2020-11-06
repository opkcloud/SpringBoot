package com.opkcloud.util.logs;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
public @interface RecordAnnon {

    String value() default "";

    String name() default "";

    Class<?> clazz() default String.class;

}
