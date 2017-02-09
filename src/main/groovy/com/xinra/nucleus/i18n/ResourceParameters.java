package com.xinra.nucleus.i18n;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Collects multiple {@link ResourceParameter}s.
 * 
 * @author Erik Hofer
 */
@Documented
@Retention(SOURCE)
@Target(FIELD)
@interface ResourceParameters {
  ResourceParameter[] value();
}
