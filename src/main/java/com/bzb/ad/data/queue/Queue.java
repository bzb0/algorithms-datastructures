package com.bzb.ad.data.queue;

import java.util.List;

public interface Queue<T> {

  /**
   * Adds the element in the queue.
   *
   * @param element The element to be added to the queue.
   * @return <code>true</code> if the element was added in the queue, <code>false</code> otherwise
   */
  boolean add(T element);

  /**
   * Removes and returns the first element from the queue. The first element is the element, that was first added to the queue.
   *
   * @return <code>null</code> if the queue is empty, or the first element in the queue.
   */
  T remove();

  /**
   * Returns a list of the elements in the queue.
   *
   * @return The queue elements as a list.
   */
  List<T> elements();

  /**
   * Returns the size of the queue.
   *
   * @return The size of the queue.
   */
  int size();
}
