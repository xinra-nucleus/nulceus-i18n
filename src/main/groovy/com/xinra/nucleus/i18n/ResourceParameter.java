package com.xinra.nucleus.i18n;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Adds a parameter to a resource string. Usage:
 * 
 * <pre>
 * {@literal @}ResourceKeys
 * public enum Foo {
 *   {@literal @}ResourceParameter(name = "foo", type = Integer.class)
 *   {@literal @}ResourceParameter(name = "bar", description = "A parameter")
 *   FOO
 * }
 * </pre>
 * 
 * <p>When adding multiple parameters to a key, the order of the annotations is used for the 
 * indices.</p>
 * 
 * @author Erik Hofer
 */
@Documented
@Retention(SOURCE)
@Target(FIELD)
@Repeatable(ResourceParameters.class)
public @interface ResourceParameter {
  
  /**
   * The name of the parameter (camel-case)
   */
  String name();
  
  /**
   * The type of the parameter
   */
  Class<?> type() default String.class;
  
  /**
   * The description of the parameter
   */
  String description() default "";
  
}
