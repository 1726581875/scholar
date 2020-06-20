package org.scholat.common.annotation;

import java.lang.annotation.*;

/**
 * @author yrk
 * @date 2020/6/17 - 16:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogFilter {

    String value() default "";
}
