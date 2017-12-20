package com.zhuazhu.frame.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:43
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientTwo {
}
