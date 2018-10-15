package com.bzb.ad.algo;

import org.junit.Assert;
import org.junit.Test;

public class BitManipulationTest {

  @Test
  public void isNumberPowerOfTwo_returnsTrueForPowerOfTwo() {
    Assert.assertTrue(BitManipulation.isPowerOfTwo(16));
    Assert.assertTrue(BitManipulation.isPowerOfTwo(64));
    Assert.assertTrue(BitManipulation.isPowerOfTwo(1024));
  }

  @Test
  public void isNumberPowerOfTwo_returnsFalseForInvalidNumbers() {
    Assert.assertFalse(BitManipulation.isPowerOfTwo(100));
    Assert.assertFalse(BitManipulation.isPowerOfTwo(50));
    Assert.assertFalse(BitManipulation.isPowerOfTwo(10000));
  }
}
