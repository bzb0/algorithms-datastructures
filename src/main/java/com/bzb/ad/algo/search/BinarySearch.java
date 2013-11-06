package com.bzb.ad.algo.search;

public class BinarySearch {

  /**
   * Returns the index in the array holding the element, that is searched. If the element is not in the array -1 will be returned.
   *
   * @param array The input array.
   * @param element The search element.
   * @param <T> The type of elements in the array.
   * @return The index of the element, or -1 if the element is not in the array.
   */
  public static <T extends Comparable<T>> int getPosition(T[] array, T element) {
    int leftIndex = 0;
    int rightIndex = array.length - 1;
    int middleIndex = (leftIndex + rightIndex) / 2;

    while (leftIndex <= rightIndex) {
      if (array[middleIndex].compareTo(element) == 0) {
        return middleIndex;
      }
      if (array[middleIndex].compareTo(element) > 0) {
        rightIndex = middleIndex - 1;
      } else {
        leftIndex = middleIndex + 1;
      }
      middleIndex = (leftIndex + rightIndex) / 2;
    }
    return -1;
  }
}
