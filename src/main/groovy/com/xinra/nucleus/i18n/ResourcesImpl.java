package com.xinra.nucleus.i18n;

import java.util.Locale;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

/**
 * Represents an instance of {@link Resources} that is bound to a specific {@link Locale}.
 * 
 * @author Erik Hofer
 */
public final class ResourcesImpl implements Resources {
  
  private final String bundle;
  private final Locale locale;
  
  /**
   * Constructs resources for a specific bundle and binds it to the locale
   * provided by {@link Resources#getDefaultLocale()}.
   * 
   * @param bundle the bundle's base name
   */
  public ResourcesImpl(String bundle) {
    this(bundle, Resources.getDefaultLocale());
  }
  
  /**
   * Constructs resources for a specific bundle and binds it to a locale.
   * 
   * @param bundle the bundle's base name
   * @param locale the locale to be used
   */
  public ResourcesImpl(String bundle, Locale locale) {
    this.bundle = bundle;
    this.locale = locale;
  }

  @Override
  public <T> T getResource(String key, Object... parameters) {
    return Resources.getResource(bundle, key, locale, parameters);
  }
  
  /**
   * Converts this object into another type using Groovy's as-coercion.
   */
  public <T> T asType(Class<T> type) {
    return DefaultGroovyMethods.asType(this, type);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bundle == null) ? 0 : bundle.hashCode());
    result = prime * result + ((locale == null) ? 0 : locale.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ResourcesImpl)) {
      return false;
    }
    ResourcesImpl other = (ResourcesImpl) obj;
    if (bundle == null) {
      if (other.bundle != null) {
        return false;
      }
    } else if (!bundle.equals(other.bundle)) {
      return false;
    }
    if (locale == null) {
      if (other.locale != null) {
        return false;
      }
    } else if (!locale.equals(other.locale)) {
      return false;
    }
    return true;
  }
}
