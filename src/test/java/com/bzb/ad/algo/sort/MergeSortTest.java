package com.bzb.ad.algo.sort;

import java.util.Random;
import java.util.stream.IntStream;
import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest {

  @Test
  public void mergeSort_sortsAnArray() {
    Random rnd = new Random();
    Integer[] array = new Integer[10_000];
    for (int i = 0; i < array.length; i++) {
      array[i] = rnd.nextInt(10_000);
    }

    MergeSort.sort(array);

    Assert.assertEquals(true, isSorted(array));
  }

  private <T extends Comparable<T>> boolean isSorted(T[] array) {
    return IntStream.range(0, array.length - 1).allMatch(i -> array[i].compareTo(array[i + 1]) <= 0);
  }
}
