package com.voler.person.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FieldInject Created by voler on 2017/5/9.
 * 说明：
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface FieldInject {
    String value();
}
