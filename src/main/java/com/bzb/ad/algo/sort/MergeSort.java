package com.bzb.ad.algo.sort;

import java.util.LinkedList;
import java.util.Objects;

public class MergeSort {

  /**
   * Sorts the input array with the merge sort algorithm.
   *
   * @param array The array to be sorted.
   */
  public static <T extends Comparable<T>> void sort(T[] array) {
    Objects.requireNonNull(array, "The provided array must not be null.");
    mergeSort(array, 0, array.length - 1);
  }

  /**
   * Sorts the array in the range [leftIndex, rightIndex].
   *
   * @param array The array to be sorted.
   * @param leftIndex The lower limit.
   * @param rightIndex The upper limit.
   */
  public static <T extends Comparable<T>> void mergeSort(T[] array, int leftIndex, int rightIndex) {
    if (leftIndex < rightIndex) {
      int middleIndex = (leftIndex + rightIndex) / 2;
      mergeSort(array, leftIndex, middleIndex);
      mergeSort(array, middleIndex + 1, rightIndex);
      merge(array, leftIndex, middleIndex, rightIndex);
    }
  }

  /**
   * Helper function for merging two sorted halves.
   *
   * @param array The array to be sorted.
   * @param leftIndex The low index.
   * @param middleIndex The middle index.
   * @param rightIndex The right index.
   */
  private static <T extends Comparable<T>> void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
    /* Instantiating and populating the partial arrays that will be merged. */
    LinkedList<T> leftHalf = new LinkedList<>();
    for (int i = leftIndex, j = 0; i <= middleIndex; i++, j++) {
      leftHalf.add(array[i]);
    }
    LinkedList<T> rightHalf = new LinkedList<>();
    for (int i = middleIndex + 1, j = 0; i <= rightIndex; i++, j++) {
      rightHalf.add(array[i]);
    }

    int i = leftIndex;
    /* Merging the arrays in the output array. */
    while (!leftHalf.isEmpty() && !rightHalf.isEmpty()) {
      array[i++] = (leftHalf.peek().compareTo(rightHalf.peek()) <= 0) ? leftHalf.poll() : rightHalf.poll();
    }

    /* Adding the residual elements. */
    while (!leftHalf.isEmpty()) {
      array[i++] = leftHalf.poll();
    }
    while (!rightHalf.isEmpty()) {
      array[i++] = rightHalf.poll();
    }
  }
}
