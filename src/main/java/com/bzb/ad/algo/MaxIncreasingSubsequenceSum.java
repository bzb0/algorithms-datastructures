package com.bzb.ad.algo;

import java.util.Objects;

public class MaxIncreasingSubsequenceSum {

  /**
   * Finds the maximum subsequence sum, in which the integers in the subsequence are sorted in increasing order.
   *
   * @param array The input array.
   * @return The maximum increasing subsequence sum.
   */
  public static int maxSum(int[] array) {
    Objects.requireNonNull(array, "The array must not be null.");
    if (array.length == 1) {
      return array[0];
    }

    int currSum = array[0], maxSum = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i - 1] <= array[i]) {
        currSum += array[i];
        maxSum = Math.max(maxSum, currSum);
      } else {
        maxSum = Math.max(maxSum, currSum);
        currSum = array[i];
      }
    }
    return maxSum;
  }
}
