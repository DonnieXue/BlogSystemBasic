package com.blog.common.annotion;

import java.lang.annotation.*;

/**
 * Created by BRL on 2018/11/10.
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
}
