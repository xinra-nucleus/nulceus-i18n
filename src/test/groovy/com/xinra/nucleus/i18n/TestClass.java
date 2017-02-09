package com.xinra.nucleus.i18n;

public class TestClass {
  
  @ResourceKeys(bundle = TestClass.class)
  public static enum Resources {
    @ResourceType(Object.class)
    JUST_A_TEST,
    ANOTHER_TEST
  }

}
