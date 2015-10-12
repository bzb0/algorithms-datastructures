package com.bzb.ad.algo;

public class StringReverse {

  public static String reverse(String string) {
    return reverse0(string, string.length() - 1);
  }

  private static String reverse0(String string, int indx) {
    if (indx < 0) {
      return "";
    }
    return string.charAt(indx) + reverse0(string, indx - 1);
  }
}
