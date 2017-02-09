package com.xinra.nucleus.i18n;

@ResourceKeys(interfaceName = "TestEnumMessages")
public enum TestEnum {
  
  /** A sample resource. */
  @ResourceParameter(name = "foo", type = String.class)
  @ResourceParameter(name = "bar", type = String.class, description = "A barameter")
  FOO,
  BAR
  
}
