package com.bzb.ad.data.heap;

public class BinaryHeap<T extends Comparable<T>> {

  private final Object[] heap;
  private int numberOfElements = 0;

  public BinaryHeap(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("The size must be a positive integer");
    }
    heap = new Object[size];
  }

  public boolean add(T element) {
    if (numberOfElements == heap.length) {
      return false;
    }

    int elementIndex = ++numberOfElements - 1;
    heap[elementIndex] = element;
    while (element.compareTo((T) heap[parent(elementIndex)]) == 1) {
      heap[elementIndex] = heap[parent(elementIndex)];
      heap[parent(elementIndex)] = element;
      elementIndex = parent(elementIndex);
    }
    return true;
  }

  public T remove() {
    if (numberOfElements == 0) {
      return null;
    }

    T result = (T) heap[0];
    heap[0] = heap[size() - 1];
    numberOfElements--;

    int elementIndex = 0;
    while (right(elementIndex) <= numberOfElements) {
      T currentElement = (T) heap[elementIndex];
      T left = (T) heap[left(elementIndex)];
      T right = (T) heap[right(elementIndex)];
      if (currentElement.compareTo(left) < 0 || currentElement.compareTo(right) < 0) {
        T tmp = (T) heap[elementIndex];
        if (left.compareTo(right) <= 0) {
          heap[elementIndex] = right;
          heap[right(elementIndex)] = tmp;
          elementIndex = right(elementIndex);
        } else {
          heap[elementIndex] = left;
          heap[left(elementIndex)] = tmp;
          elementIndex = left(elementIndex);
        }
      } else {
        break;
      }
    }
    return result;
  }

  public int size() {
    return numberOfElements;
  }

  private int left(int index) {
    return 2 * index + 1;
  }

  private int right(int index) {
    return 2 * index + 2;
  }

  private int parent(int index) {
    return (index - 1) / 2;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < numberOfElements; i++) {
      sb.append(heap[i]);
      sb.append(i + 1 < numberOfElements ? ", " : "");
    }
    return sb.append("]").toString();
  }
}
