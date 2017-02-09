package com.xinra.nucleus.i18n;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotate a resource key to indicate that the type of the resource
 * is different from {@link String}.
 * 
 * @author Erik Hofer
 */
@Documented
@Retention(SOURCE)
@Target(FIELD)
public @interface ResourceType {
  
  /**
   * The type of the resource
   */
  Class<?> value();

}
