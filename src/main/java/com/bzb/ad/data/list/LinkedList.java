package com.bzb.ad.data.list;

import java.util.Objects;

/**
 * A simple implementation of a single linked list.
 *
 * @param <T> The type of the elements in the list.
 */
public class LinkedList<T> {

  // Head of the linked list
  private Node<T> headNode;

  /**
   * Single element in the linked list.
   *
   * @param <T> The element type.
   */
  private static class Node<T> {

    // The data this node holds
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
   * Adds the element at the end of the list.
   *
   * @param element The element to be added to the list.
   */
  public void put(T element) {
    Objects.requireNonNull(element, "The provided element must not be NULL.");
    // Creating a node to hold the element
    Node<T> node = new Node<T>(element);
    if(headNode == null) {
      headNode = node;
    } else {
      Node<T> currNode = headNode;
      Node<T> prevNode = null;
      // We iterate until the end of the list
      while (currNode != null) {
        prevNode = currNode;
        currNode = currNode.nextNode;
      }
      // Placing the element at the end of list
      node.nextNode = prevNode.nextNode;
      prevNode.nextNode = node;
    }
  }

  /**
   * Removes the head of the list.
   *
   * @return The element at the start (head) of the list.
   */
  public T take() {
    Node<T> node = getHeadNode();
    return (node != null) ? node.item : null;
  }

  /**
   * Returns the head of the list, and moves the head of the list to the next element in the list.
   *
   * @return Reference to the head of the list.
   */
  private Node<T> getHeadNode() {
    Node<T> node = headNode;
    if (node != null) {
      headNode = node.nextNode;
      return node;
    }
    return null;
  }
}
