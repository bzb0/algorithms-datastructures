package com.bzb.ad.algo.math;

public class SquareRoot {

  /**
   * Calculates a square root of a number with the babylonian formula. X is equal to N as initial approximation, while E represents the accuracy
   * level.
   *
   * @param n The input number
   * @return The square root.
   */
  public static double squareRoot(double n) {
    double x = n, y = 1, e = 0.000001f;
    while (x - y > e) {
      x = (x + y) / 2;
      y = n / x;
    }
    return x;
  }
}
