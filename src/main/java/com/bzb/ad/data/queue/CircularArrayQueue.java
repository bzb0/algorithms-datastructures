package com.bzb.ad.data.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CircularArrayQueue<T> implements Queue<T> {

  private T[] elements;

  private int firstIndex = 0;
  private int lastIndex = 0;
  private int numberOfElements = 0;

  public CircularArrayQueue(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("The size of the queue must be a positive integer.");
    }
    elements = (T[]) new Object[size];
  }

  public boolean add(T element) {
    Objects.requireNonNull(element, "The element must not be NULL");
    // The queue is full
    if (firstIndex == lastIndex && numberOfElements > 0) {
      return false;
    }

    elements[lastIndex] = element;
    lastIndex = ++lastIndex % elements.length;
    numberOfElements++;
    return true;
  }

  public T remove() {
    // The queue is empty
    if (numberOfElements == 0) {
      return null;
    }

    T result = elements[firstIndex];
    elements[firstIndex] = null;
    firstIndex = ++firstIndex % elements.length;
    numberOfElements--;
    return result;
  }

  @Override
  public List<T> elements() {
    if (numberOfElements == 0) {
      return Collections.emptyList();
    }

    if (firstIndex < lastIndex) {
      List<T> result = new ArrayList<>();
      for (int i = firstIndex; i < lastIndex; i++) {
        result.add(elements[i]);
      }
      return result;
    }

    List<T> result = new ArrayList<>();
    for (int i = firstIndex; i < elements.length; i++) {
      result.add(elements[i]);
    }
    for (int i = 0; i < lastIndex; i++) {
      result.add(elements[i]);
    }
    return result;
  }

  @Override
  public int size() {
    return numberOfElements;
  }

  @Override
  public String toString() {
    if (numberOfElements == 0) {
      return "[]";
    }

    if (firstIndex < lastIndex) {
      StringBuilder builder = new StringBuilder("[");
      for (int i = firstIndex; i < lastIndex; i++) {
        builder.append(elements[i]);
        builder.append(i + 1 < lastIndex ? ", " : "");
      }
      return builder.append("]").toString();
    }

    StringBuilder builder = new StringBuilder("[");
    for (int i = firstIndex; i < elements.length; i++) {
      builder.append(elements[i] + ", ");
    }
    for (int i = 0; i < lastIndex; i++) {
      builder.append(elements[i]);
      builder.append(i + 1 < lastIndex ? ", " : "");
    }
    return builder.append("]").toString();
  }
}
