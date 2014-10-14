package com.bzb.ad.algo.math;

import org.junit.Assert;
import org.junit.Test;

public class PalindromeTest {

  @Test
  public void isPalindrome_ReturnsTrue_ForPalindromeNumbers() {
    Assert.assertEquals(true, Palindrome.isPalindrome(155121551));
  }

  @Test
  public void isPalindrome_ReturnsFalse_ForPalindromeNumbers() {
    Assert.assertEquals(false, Palindrome.isPalindrome(123456789));
  }

  @Test
  public void checkSignReturnsOneForPositiveIntegers() {
    Assert.assertEquals(1, Palindrome.checkSign(1005));
  }
}
