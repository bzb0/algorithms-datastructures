package com.bzb.ad.algo;

import java.util.List;
import java.util.Random;

public class FisherYatesArrayShuffle {

  /**
   * Shuffles the input list with  the Fisher-Yates algorithm.
   *
   * @param list The list to be shuffled.
   * @return The shuffled list.
   */
  public static <T> List<T> shuffle(List<T> list) {
    Random rnd = new Random();
    for (int i = list.size() - 1; i > 0; i--) {
      // Swapping index I with a random index (0 to i)
      swap(list, i, rnd.nextInt(i + 1));
    }
    return list;
  }

  /**
   * Auxiliary function, which swaps the elements on the specified indices.
   *
   * @param list The input list.
   * @param i Index i.
   * @param j Index j.
   */
  private static <T> void swap(List<T> list, int i, int j) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }
}
