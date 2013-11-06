package com.bzb.ad.algo.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

public class InterpolationSearchTest {

  @Test
  public void interpolationSearchFindsElementAndReturnsPosition() {
    Random rnd = new Random();
    Set<Integer> numbers = new HashSet<>();
    for (int i = 0; i < 10_000; i++) {
      numbers.add(rnd.nextInt(10_000));
    }
    int[] array = numbers.stream().mapToInt(i -> i).toArray();
    int expectedIndex = rnd.nextInt(array.length);
    int elementToSearch = array[expectedIndex];

    Arrays.sort(array);

    Assert.assertEquals(expectedIndex, InterpolationSearch.getPosition(array, elementToSearch));
  }
}
