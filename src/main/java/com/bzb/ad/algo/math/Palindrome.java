package com.bzb.ad.algo.math;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

  /**
   * Checks if the given number is palindrome.
   *
   * @param number The input number;
   * @return <code>true</code> if the number is palindrom, <code>false</code> otherwise.
   */
  public static boolean isPalindrome(int number) {
    List<Integer> ints = new ArrayList<>();
    while (number != 0) {
      ints.add(number % 10);
      number = number / 10;
    }

    int i = 0, j = ints.size() - 1;
    while (i < j) {
      if (ints.get(i) != ints.get(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static int checkSign(int number) {
    if (number == 0) {
      return 1;
    }
    if (number >> 31 != 0) {
      return -1;
    }
    return 1;
  }
}
