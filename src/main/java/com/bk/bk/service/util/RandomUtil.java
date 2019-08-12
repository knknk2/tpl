package com.bk.bk.service.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

  private static final int DEF_COUNT = 20;

  private RandomUtil() {
  }

  /**
   * Generate a password.
   *
   * @return the generated password.
   */
  public static String generatePassword() {
    return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
  }

  /**
   * Generate an random key.
   *
   * @return the generated random key. 
   */
  public static String generateKey() {
    return RandomStringUtils.randomNumeric(DEF_COUNT);
  }
}
