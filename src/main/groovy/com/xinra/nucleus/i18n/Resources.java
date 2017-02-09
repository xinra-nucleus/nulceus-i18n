package com.xinra.nucleus.i18n;

import com.xinra.nucleus.common.ApplicationContextProvider;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

/**
 * Provides static methods to retrieve internationalized resources.
 * 
 * <p>Supertype for generated resources interfaces.</p>
 * 
 * @author Erik Hofer
 */
public interface Resources {
  
  /**
   * Retrieves a resource. Bundle and locale are determined by the implementation.
   * 
   * @param key the key of the resource
   * @param parameters the parameters that should be applied to a string resource
   * @param <T> the type of the resource
   * @return the resource (with applied parameters)
   * @throws NullPointerException if the {@code key} is {@code null}
   * @throws MissingResourceException if no resource for the given {@code key} can be found
   * @throws IllegalArgumentException if there are {@code parameters} but the resource 
   *     is not a string
   * @throws IllegalArgumentException if the parameters couldn't be applied because the format
   *     of the resource string or the {@code parameters} were invalid
   */
  <T> T getResource(String key, Object... parameters);
  
  /**
   * Retrieves a resource using {@link ResourceBundle} using the locale 
   * returned by {@link #getDefaultLocale()}.
   * 
   * @param key the key of the resource
   * @param parameters the parameters that should be applied to a string resource
   * @param <T> the type of the resource
   * @return the resource (with applied parameters)
   * @throws NullPointerException if the {@code key} is {@code null}
   * @throws MissingResourceException if no resource for the given {@code key} can be found
   * @throws IllegalArgumentException if there are {@code parameters} but the resource 
   *     is not a string
   * @throws IllegalArgumentException if the parameters couldn't be applied because the format
   *     of the resource string or the {@code parameters} were invalid
   * @throws IllegalArgumentException if the {@code key}'s enum is not annotated 
   *     with {@link ResourceKeys}
   */
  static <T> T getResource(Enum<?> key, Object... parameters) {
    return getResource(key, getDefaultLocale(), parameters);
  }
  
  /**
   * Retrieves a resource using {@link ResourceBundle}.
   * 
   * @param key the key of the resource
   * @param parameters the parameters that should be applied to a string resource
   * @param <T> the type of the resource
   * @return the resource (with applied parameters)
   * @throws NullPointerException if the {@code key} is {@code null}
   * @throws MissingResourceException if no resource for the given {@code key} can be found
   * @throws IllegalArgumentException if there are {@code parameters} but the resource 
   *     is not a string
   * @throws IllegalArgumentException if the parameters couldn't be applied because the format
   *     of the resource string or the {@code parameters} were invalid
   * @throws IllegalArgumentException if the {@code key}'s enum is not annotated 
   *     with {@link ResourceKeys}
   */
  static <T> T getResource(Enum<?> key, Locale locale, Object... parameters) {
    return getResource(getBundle(key.getClass()), key.toString(), parameters);
  }
  
  /**
   * Retrieves a resource using {@link ResourceBundle} using the locale 
   * returned by {@link #getDefaultLocale()}.
   * 
   * @param bundle the bundle class
   * @param key the key of the resource
   * @param parameters the parameters that should be applied to a string resource
   * @param <T> the type of the resource
   * @return the resource (with applied parameters)
   * @throws NullPointerException if the {@code key} is {@code null}
   * @throws MissingResourceException if no resource for the given {@code key} can be found
   * @throws IllegalArgumentException if there are {@code parameters} but the resource 
   *     is not a string
   * @throws IllegalArgumentException if the parameters couldn't be applied because the format
   *     of the resource string or the {@code parameters} were invalid
   */
  static <T> T getResource(Class<?> bundle, String key, Object... parameters) {
    return getResource(bundle, key, getDefaultLocale(), parameters);
  }
  
  /**
   * Retrieves a resource using {@link ResourceBundle}.
   * 
   * @param bundle the bundle class
   * @param key the key of the resource
   * @param locale the locale to be used
   * @param parameters the parameters that should be applied to a string resource
   * @param <T> the type of the resource
   * @return the resource (with applied parameters)
   * @throws NullPointerException if the {@code key} is {@code null}
   * @throws MissingResourceException if no resource for the given {@code key} can be found
   * @throws IllegalArgumentException if there are {@code parameters} but the resource 
   *     is not a string
   * @throws IllegalArgumentException if the parameters couldn't be applied because the format
   *     of the resource string or the {@code parameters} were invalid
   */
  static <T> T getResource(Class<?> bundle, String key, Locale locale, Object... parameters) {
    return getResource(getBundleName(bundle), key, locale, parameters);
  }
  
  /**
   * Retrieves a resource using {@link ResourceBundle} using the locale 
   * returned by {@link #getDefaultLocale()}.
   * 
   * @param bundle the base name of the bundle
   * @param key the key of the resource
   * @param parameters the parameters that should be applied to a string resource
   * @param <T> the type of the resource
   * @return the resource (with applied parameters)
   * @throws NullPointerException if the {@code key} is {@code null}
   * @throws MissingResourceException if no resource for the given {@code key} can be found
   * @throws IllegalArgumentException if there are {@code parameters} but the resource 
   *     is not a string
   * @throws IllegalArgumentException if the parameters couldn't be applied because the format
   *     of the resource string or the {@code parameters} were invalid
   */
  static <T> T getResource(String bundle, String key, Object... parameters) {
    return getResource(bundle, key, getDefaultLocale(), parameters);
  }
  
  /**
   * Retrieves a resource using {@link ResourceBundle}.
   * 
   * @param bundle the base name of the bundle
   * @param key the key of the resource
   * @param locale the locale to be used
   * @param parameters the parameters that should be applied to a string resource
   * @param <T> the type of the resource
   * @return the resource (with applied parameters)
   * @throws NullPointerException if the {@code key} is {@code null}
   * @throws MissingResourceException if no resource for the given {@code key} can be found
   * @throws IllegalArgumentException if there are {@code parameters} but the resource 
   *     is not a string
   * @throws IllegalArgumentException if the parameters couldn't be applied because the format
   *     of the resource string or the {@code parameters} were invalid
   */
  @SuppressWarnings("unchecked")
  static <T> T getResource(String bundle, String key, Locale locale, Object... parameters) {
    Object resource = ResourceBundle.getBundle(bundle, locale).getObject(key);
    if (resource instanceof String) {
      resource = new MessageFormat((String) resource, locale).format(parameters);
    } else if (parameters.length > 0) {
      throw new IllegalArgumentException("Arguments couldn't be apllied because the "
          + "resource is not a string!");
    }
    return (T) resource;
  }
  
  /**
   * Returns the locale that is used when getting a resource without specifying a locale.
   * If there is a bean of type {@link LocaleProvider} in the application context it is
   * used to determine the locale, otherwise {@link Locale#getDefault()} is used.
   */
  static Locale getDefaultLocale() {
    ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
    if (applicationContext != null) {
      try {
        return applicationContext.getBean(LocaleProvider.class).getLocale();
      } catch (NoSuchBeanDefinitionException ex) {
        //ignore
      }
    }
    return Locale.getDefault();
  }
  
  /**
   * Converts a bundle class to its string representation.
   */
  static String getBundleName(Class<?> bundle) {
    return bundle.getName();
  }
  
  /**
   * Returns the bundle class of a resource key definition.
   * 
   * @param resourceKeys an enum annotated with {@link ResourceKeys}
   * @return the bundle class
   * @throws IllegalArgumentException if the enum is not annotated with {@link ResourceKeys}.
   */
  @SuppressWarnings("rawtypes")
  static Class<?> getBundle(Class<? extends Enum> resourceKeys) {
    ResourceKeys annotation = resourceKeys.getAnnotation(ResourceKeys.class);
    if (annotation == null) {
      throw new IllegalArgumentException("The enum is not annotated with @ResourceKeys!");
    }
    return annotation.bundle() == Self.class ? resourceKeys : annotation.bundle();
  }
}
