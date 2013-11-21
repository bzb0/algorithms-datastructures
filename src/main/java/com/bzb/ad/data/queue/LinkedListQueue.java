package com.bzb.ad.data.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A queue implementation, which stores the elements in a linked list.
 *
 * @param <T> The type of the elements in the queue.
 */
public class LinkedListQueue<T> implements Queue<T> {

  // Head of the queue
  private Node<T> headNode;

  private int numberOfElements;

  /**
   * Represents a single element in the queue.
   *
   * @param <T> The element type.
   */
  private static class Node<T> {

    // The node data
    T item;

    // Reference to the next node
    Node<T> nextNode;

    Node(T t) {
      item = t;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Node [");
      sb.append("item=").append(item);
      sb.append(", nextNode=").append(nextNode);
      sb.append(']');
      return sb.toString();
    }
  }

  /**
   * Inserts an element in the queue.
   *
   * @param item The item to be added to the queue.
   */
  public boolean add(T item) {
    if (item == null) {
      return false;
    }
    Node<T> node = new Node<>(item);
    if (headNode == null) {
      headNode = node;
      numberOfElements++;
      return true;
    }
    // We iterate till the end of the list
    Node<T> currNode = headNode;
    while (currNode.nextNode != null) {
      currNode = currNode.nextNode;
    }
    currNode.nextNode = node;
    numberOfElements++;
    return true;
  }

  /**
   * Removes the head of the queue.
   *
   * @return The element at the head (start) of the queue.
   */
  public T remove() {
    if (headNode == null) {
      return null;
    }
    Node<T> currHead = headNode;
    headNode = headNode.nextNode;
    currHead.nextNode = null;
    numberOfElements--;
    return currHead.item;
  }

  @Override
  public List<T> elements() {
    if (headNode == null) {
      return Collections.emptyList();
    }

    List<T> elements = new ArrayList<>();
    Node<T> currNode = headNode;
    while (currNode.nextNode != null) {
      elements.add(currNode.item);
      currNode = currNode.nextNode;
    }
    return elements;
  }

  @Override
  public int size() {
    return numberOfElements;
  }
}