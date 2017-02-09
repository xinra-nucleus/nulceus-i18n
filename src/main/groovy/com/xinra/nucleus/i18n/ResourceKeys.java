package com.xinra.nucleus.i18n;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Elements of an annotated enum are used as resource keys.
 * 
 * @author Erik Hofer
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ResourceKeys {
  
  /**
   * The class to which the resource keys defined in this enum belong.
   */
  Class<?> bundle() default Self.class;
  
  /**
   * The name of the generated resource interface. By default, the interface name
   * for the bundle {@code Foo} is {@code FooResources} in the same package. If the
   * interface name is not fully qualified, it will be placed in the same package
   * as the bundle.
   */
  String interfaceName() default "";

}
