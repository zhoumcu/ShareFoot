package com.ar.pay.sharefoot.service;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author：Administrator on 2017/3/14 16:00
 * company: xxxx
 * email：1032324589@qq.com
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    String value();
}
