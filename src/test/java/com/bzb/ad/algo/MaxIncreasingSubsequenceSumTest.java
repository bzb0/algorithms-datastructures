package com.bzb.ad.algo;

import org.junit.Assert;
import org.junit.Test;

public class MaxIncreasingSubsequenceSumTest {

  @Test
  public void maxSum_findsMaxIncreasingSubsequenceSum() {
    Assert.assertEquals(5001, MaxIncreasingSubsequenceSum.maxSum(
        new int[]{100, 5, 4, 3, 2, 4, 6, 0, 10, 12, 14, 13, 500, 1, 2, 3, 4, 1000, 1, 5000}));
    Assert.assertEquals(11, MaxIncreasingSubsequenceSum.maxSum(new int[]{11, 8, 2, 1}));
    Assert.assertEquals(105, MaxIncreasingSubsequenceSum.maxSum(new int[]{1, 101, 2, 4, 99, 4, 50}));
  }
}
