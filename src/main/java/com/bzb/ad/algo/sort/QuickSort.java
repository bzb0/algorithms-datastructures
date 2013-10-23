package com.bzb.ad.algo.sort;

import java.util.Objects;

public class QuickSort {

  /**
   * Sorts the input array with the quick sort algorithm.
   *
   * @param array The array to be sorted.
   */
  public static <T extends Comparable<T>> void sort(T[] array) {
    Objects.requireNonNull(array, "The provided array must not be null.");
    quickSort(array, 0, array.length - 1);
  }

  /**
   * Sorts the array in the range [leftIndex, rightIndex].
   *
   * @param array The array to be sorted.
   * @param leftIndex The left index.
   * @param rightIndex The right index.
   * @param <T> The element type.
   */
  private static <T extends Comparable<T>> void quickSort(T[] array, int leftIndex, int rightIndex) {
    // We execute the quick sort algorithm, only if the leftIndex and rightIndex are not overlapping.
    if (leftIndex < rightIndex) {
      int pivotIndex = partition(array, leftIndex, rightIndex);
      quickSort(array, leftIndex, pivotIndex - 1);
      quickSort(array, pivotIndex + 1, rightIndex);
    }
  }

  /**
   * Searches for the pivot element, the element which is in the middle of the sorted array. All elements smaller than the pivot will left from it and
   * all elements bigger will be right from it.
   *
   * @param array The array to be sorted.
   * @param leftIndex The left/lower index.
   * @param rightIndex The right/upper index.
   * @return The pivot index.
   */
  private static <T extends Comparable<T>> int partition(T[] array, int leftIndex, int rightIndex) {
    int pivotIndex = leftIndex;
    for (int i = leftIndex; i < rightIndex; i++) {
      if (array[i].compareTo(array[rightIndex]) < 0) {
        swap(array, i, pivotIndex++);
      }
    }
    swap(array, pivotIndex, rightIndex);
    return pivotIndex;
  }

  /**
   * Swaps two elements in the array.
   *
   * @param array The input array.
   * @param i The first index.
   * @param j The second index.
   */
  private static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
    T acc = array[j];
    array[j] = array[i];
    array[i] = acc;
  }
}
