package com.bzb.ad.algo.math;

public class CatalanNumber {

  public static int catalanNumber(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }

    int catalanNumbers[] = new int[n + 1];
    catalanNumbers[0] = 1;
    catalanNumbers[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        catalanNumbers[i] += catalanNumbers[j] * catalanNumbers[i - 1 - j];
      }
    }
    return catalanNumbers[n];
  }
}
