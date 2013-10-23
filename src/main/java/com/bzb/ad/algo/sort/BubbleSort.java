package com.bzb.ad.algo.sort;

import java.util.Objects;

public class BubbleSort {

  /**
   * Sorts the input array with the bubble sort algorithm.
   *
   * @param array The array to be sorted.
   */
  public static <T extends Comparable<T>> void sort(T[] array) {
    Objects.requireNonNull(array, "The provided array must not be null.");
    for (int i = 0; i < array.length - 1; i++) {
      for (int j = 0; j < array.length - i - 1; j++) {
        if ((array[j]).compareTo(array[j + 1]) > 0) {
          T temp = array[j + 1];
          array[j + 1] = array[j];
          array[j] = temp;
        }
      }
    }
  }
}
