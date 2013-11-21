package com.bzb.ad.data.queue;

import org.junit.Assert;
import org.junit.Test;

public class CircularArrayQueueTest {

  @Test
  public void add_addsSingleElement() {
    Integer element = 10;
    CircularArrayQueue<Integer> sut = new CircularArrayQueue<>(10);

    boolean addResult = sut.add(element);

    Assert.assertEquals(true, addResult);
    Assert.assertEquals(element, sut.remove());
  }

  @Test
  public void add_addsMultipleElements() {
    Integer[] elements = new Integer[]{1, 2, 3, 4, 5};
    CircularArrayQueue<Integer> sut = new CircularArrayQueue<>(10);

    boolean addResult = true;
    for (Integer element : elements) {
      addResult &= sut.add(element);
    }

    for (Integer expectedElement : elements) {
      Integer removedElement = sut.remove();
      Assert.assertEquals(expectedElement, removedElement);
    }
    Assert.assertEquals(true, addResult);
  }

  @Test
  public void addRemove_addsAndRemovesMultipleElements() {
    Integer[] elements = new Integer[]{1, 2, 3, 4, 5};
    CircularArrayQueue<Integer> sut = new CircularArrayQueue<>(10);

    boolean addResult = true;
    for (Integer element : elements) {
      addResult &= sut.add(element);
    }
    sut.remove();
    sut.remove();

    for (int i = 2; i < elements.length; i++) {
      Assert.assertEquals(elements[i], sut.remove());
    }
    Assert.assertEquals(true, addResult);
  }

  @Test
  public void addRemove_addsAndRemovesElementsInterleaved() {
    Integer[] elements = new Integer[]{1, 2, 3, 4, 5};
    CircularArrayQueue<Integer> sut = new CircularArrayQueue<>(10);

    boolean addResult = true;
    for (Integer element : elements) {
      addResult &= sut.add(element);
    }
    sut.remove();
    sut.remove();

    addResult &= sut.add(elements[0]);
    addResult &= sut.add(elements[1]);

    for (int i = 2; i < elements.length; i++) {
      Assert.assertEquals(elements[i], sut.remove());
    }
    for (int i = 0; i < 2; i++) {
      Assert.assertEquals(elements[i], sut.remove());
    }

    Assert.assertEquals(true, addResult);
  }

  @Test
  public void remove_ReturnsNullOnEmptyQueue() {
    Assert.assertEquals(null, new CircularArrayQueue<>(10).remove());
  }

  @Test(expected = NullPointerException.class)
  public void add_throwsNullPointerException_nullElement() {
    new CircularArrayQueue<>(10).add(null);
  }
}
