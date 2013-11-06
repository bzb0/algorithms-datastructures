package com.bzb.ad.algo.search;

public class InterpolationSearch {

  /**
   * Returns the index in the array holding the element, that is searched. If the element is not in the array -1 will be returned.
   *
   * @param array The input array.
   * @param element The search element.
   * @return The index of the element, or -1 if the element is not in the array.
   */
  public static int getPosition(int[] array, int element) {
    int leftIndex = 0, rightIndex = array.length - 1, middleIndex;
    int low = array[0], high = array[array.length - 1];

    while (leftIndex < rightIndex) {
      middleIndex = leftIndex + ((rightIndex - leftIndex) * (element - low)) / (high - low);
      if (element == array[middleIndex]) {
        return middleIndex;
      }
      if (element < array[middleIndex]) {
        rightIndex = middleIndex - 1;
        high = array[middleIndex];
      } else {
        leftIndex = middleIndex + 1;
        low = array[middleIndex];
      }
    }
    return element == array[leftIndex] ? leftIndex : -1;
  }
}
