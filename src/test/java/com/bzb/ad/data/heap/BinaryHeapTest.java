package com.bzb.ad.data.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;

public class BinaryHeapTest {

  @Test
  public void add_addsElementsToHeap_removeReturnsBiggestElement() {
    Random rnd = new Random();
    Set<Integer> numbers = new TreeSet<>(Comparator.reverseOrder());
    for (int i = 0; i < 10_000; i++) {
      numbers.add(rnd.nextInt(10_000));
    }
    List<Integer> list = new ArrayList<>(numbers);
    Collections.shuffle(list);

    BinaryHeap<Integer> heap = new BinaryHeap<>(list.size());
    list.forEach(elem -> heap.add(elem));

    numbers.forEach(element -> Assert.assertEquals(element, heap.remove()));
  }
}
