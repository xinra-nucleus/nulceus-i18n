package com.xinra.nucleus.i18n;

import java.util.Locale;

/**
 * If a bean of this type is present, it is used to determine the default
 * locale used by {@link Resources}.
 * 
 * @author Erik Hofer
 */
public interface LocaleProvider {
  
  Locale getLocale();

}
