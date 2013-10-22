package com.bzb.ad.data.list;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

  LinkedList<Integer> sut = new LinkedList<>();

  @Test
  public void put_AddsSingleElement() {
    Integer element = 5;

    sut.put(element);

    Assert.assertEquals(element, sut.take());
  }

  @Test
  public void put_AddsMultipleElements() {
    Integer[] elements = new Integer[]{1, 2, 3, 4, 5};

    for (int i = 0; i < elements.length; i++) {
      sut.put(elements[i]);
    }

    for (int i = 0; i < elements.length; i++) {
      Assert.assertEquals(elements[i], sut.take());
    }
  }

  @Test(expected = NullPointerException.class)
  public void put_throwsNullPointerException_nullElement() {
    sut.put(null);
  }
}
