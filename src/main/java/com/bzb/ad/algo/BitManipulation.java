package com.bzb.ad.algo;

public class BitManipulation {

  /**
   * Checks if a number is power of two.
   *
   * @param number The input number.
   * @return <code>true</code> if the number is power of two, <code>false</code> otherwise.
   */
  public static boolean isPowerOfTwo(int number) {
    return (number & (number - 1)) == 0;
  }

  /**
   * Calculates how many 1s are in the binary representation of the number.
   *
   * @param number The input number.
   * @return Number of 1s in the number's binary representation.
   */
  public static int numberOfOnesInBinaryRepresentation(int number) {
    int cnt = 0;
    do {
      number = number & number - 1;
      cnt++;
    } while (number != 0);
    return cnt;
  }
}
