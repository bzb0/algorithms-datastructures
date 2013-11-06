package com.bzb.ad.algo.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void binarySearchFindsElementAndReturnsPosition() {
    Random rnd = new Random();
    Set<Integer> numbers = new HashSet<>();
    for (int i = 0; i < 10_000; i++) {
      numbers.add(rnd.nextInt(10_000));
    }
    Integer[] array = numbers.toArray(new Integer[0]);
    int expectedIndex = rnd.nextInt(array.length);
    int elementToSearch = array[expectedIndex];

    Arrays.sort(array);

    Assert.assertEquals(expectedIndex, BinarySearch.getPosition(array, elementToSearch));
  }
}
