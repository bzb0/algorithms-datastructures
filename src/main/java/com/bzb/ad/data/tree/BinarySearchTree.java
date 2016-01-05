package com.bzb.ad.data.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BinarySearchTree<T extends Comparable<T>> {

  private static class Node<T extends Comparable<T>> {

    private T data;
    private Node<T> left, right, parent;

    public Node(T value) {
      data = value;
      left = right = parent = null;
    }

    boolean isLeaf() {
      return this.left == null && this.right == null;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Node[");
      sb.append("data=").append(data);
      sb.append(", left=").append(left);
      sb.append(", right=").append(right);
      sb.append(", parent=").append(parent);
      sb.append(']');
      return sb.toString();
    }
  }

  private Node<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public boolean add(T element) {
    Node<T> newNode = new Node<T>(element);
    if (root == null) {
      root = newNode;
      return true;
    }
    Node<T> current = root, prev = root;
    while (current != null) {
      prev = current;
      if (current.data.compareTo(element) > 0) {
        current = current.left;
      } else if (current.data.compareTo(element) < 0) {
        current = current.right;
      }
    }
    if (prev.data.compareTo(element) < 0) {
      prev.right = newNode;
      newNode.parent = prev;
      return true;
    }
    if (prev.data.compareTo(element) > 0) {
      prev.left = newNode;
      newNode.parent = prev;
      return true;
    }
    return false;
  }

  public int size() {
    Deque<Node> stack = new ArrayDeque<>();
    Node<T> current = root;
    int size = 0;
    stack.push(root);
    while (!stack.isEmpty()) {
      if (current != null) {
        stack.push(current);
        current = current.left;
      } else {
        size++;
        current = stack.pop();
        current = current.right;
      }
    }
    return size - 1;
  }

  public String toStringPreorder() {
    StringBuilder builder = new StringBuilder();
    Deque<Node<T>> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      Node<T> current = stack.pop();
      builder.append("->" + current.data);
      if (current.right != null) {
        stack.push(current.right);
      }
      if (current.left != null) {
        stack.push(current.left);
      }
    }
    return builder.toString();
  }

  public String toStringInorder() {
    StringBuilder builder = new StringBuilder();
    Deque<Node<T>> stack = new ArrayDeque<>();
    Node<T> curr = root;
    while (!stack.isEmpty() || curr != null) {
      if (curr != null) {
        stack.push(curr);
        curr = curr.left;
      } else {
        curr = stack.pop();
        builder.append("->" + curr.data);
        curr = curr.right;
      }
    }
    return builder.toString();
  }

  public String toStringPostorder() {
    StringBuilder builder = new StringBuilder();
    Deque<Node<T>> stack = new ArrayDeque<>();
    Node<T> current = root;
    Set<Node<T>> visited = new HashSet<>();
    stack.push(current);
    visited.add(current);
    while (!stack.isEmpty() || current != null) {
      if (current != null) {
        if (current.right != null && !visited.contains(current.right)) {
          stack.push(current.right);
          visited.add(current.right);
        }
        if (current.left != null && !visited.contains(current.left)) {
          stack.push(current.left);
          visited.add(current.left);
        }
        current = current.left;
      } else {
        current = stack.pop();
        builder.append("->" + current.data);
      }
    }
    return builder.toString();
  }

  public String toStringBFS() {
    StringBuilder builder = new StringBuilder();
    Deque<Node<T>> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      Node<T> current = queue.removeFirst();
      builder.append("->" + current.data);
      if (current.left != null) {
        queue.addLast(current.left);
      }
      if (current.right != null) {
        queue.addLast(current.right);
      }
    }
    return builder.toString();
  }

  public String toStringDFS() {
    StringBuilder builder = new StringBuilder();
    Deque<Node<T>> stack = new ArrayDeque<>();
    Set<Node<T>> visited = new HashSet<>();

    stack.add(root);
    visited.add(root);
    Node<T> current = root;

    builder.append("->" + current.data);
    while (!stack.isEmpty()) {
      current = stack.peek();
      if (current.left != null && !visited.contains(current.left)) {
        stack.push(current.left);
        visited.add(current.left);
        builder.append("->" + current.left.data);
        continue;
      }
      if (current.right != null && !visited.contains(current.right)) {
        stack.push(current.right);
        visited.add(current.right);
        builder.append("->" + current.right.data);

        continue;
      }
      stack.pop();
    }
    return builder.toString();
  }

  public String toStringLeaves() {
    StringBuilder builder = new StringBuilder();
    Deque<Node<T>> stack = new ArrayDeque<>();
    Node<T> curr = root;
    while (!stack.isEmpty() || curr != null) {
      if (curr != null) {
        stack.push(curr);
        curr = curr.left;
      } else {
        curr = stack.pop();
        if (curr.left == null && curr.right == null) {
          builder.append("->" + curr.data);
        }
        curr = curr.right;
      }
    }
    return builder.toString();
  }

  public T getInorderSuccessor(T element) {
    if (root == null) {
      return null;
    }
    Node<T> current = root;
    while (current.data.compareTo(element) != 0) {
      if (current.data.compareTo(element) > 0) {
        current = current.left;
      } else if (current.data.compareTo(element) < 0) {
        current = current.right;
      }
    }
    if (current.right == null) {
      while (current != null && current.data.compareTo(element) <= 0) {
        current = current.parent;
      }
      return current == null ? null : current.data;
    } else {
      current = current.right;
      Node<T> prev = current;
      while (current != null) {
        prev = current;
        current = current.left;
      }
      return prev.data;
    }
  }

  public boolean sameInorderTraversal(BinarySearchTree tree) {
    List<T> thisTreeOutput = new ArrayList<>();
    Deque<Node<T>> stack = new ArrayDeque<>();
    Node<T> curr = root;
    while (!stack.isEmpty() || curr != null) {
      if (curr != null) {
        stack.push(curr);
        curr = curr.left;
      } else {
        curr = stack.pop();
        thisTreeOutput.add(curr.data);
        curr = curr.right;
      }
    }

    curr = tree.root;
    int i = 0;
    while (!stack.isEmpty() || curr != null) {
      if (curr != null) {
        stack.push(curr);
        curr = curr.left;
      } else {
        curr = stack.pop();
        if (thisTreeOutput.get(i) != curr.data) {
          return false;
        }
        i++;
        curr = curr.right;
      }
    }
    return true;
  }
}
