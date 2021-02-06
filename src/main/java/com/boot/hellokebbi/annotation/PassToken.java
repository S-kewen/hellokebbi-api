package com.boot.hellokebbi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @PackageName: com.boot.hellokebbi.annotation
 * @ClassName: PassToken
 * @Description: This is PassToken interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-1 2:25
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}

