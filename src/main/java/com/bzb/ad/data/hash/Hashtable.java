package com.bzb.ad.data.hash;

/**
 * Simple implementation of a hashtable in Java. This class corresponds to Java's HashMap class. The Hashtable class in Java is a thread-safe
 * implementation of a key-value map.
 *
 * @author Bogdan Zafirov
 */
public class Hashtable<K, V> {

  // Array holding the keys
  private final K keys[];

  // Array holding the values
  private final V values[];

  // Size of the hashtable
  private int size = 16;

  public Hashtable() {
    keys = (K[]) new Object[size];
    values = (V[]) new Object[size];
  }

  public Hashtable(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("The size must be a positive integer");
    }
    this.size = size;
    keys = (K[]) new Object[size];
    values = (V[]) new Object[size];
  }

  /**
   * Adds a key-value mapping in the table.
   *
   * @param key The object to be mapped.
   * @param value The value associated with the object.
   * @return True if the key can be stored in the hash table, false otherwise.
   */
  public boolean add(K key, V value) {
    if (key == null) {
      return false;
    }
    // Searching for a free slot
    for (int i = 0; i < size; i++) {
      int index = hashCode(key, i);
      if (keys[index] == null) {
        keys[index] = key;
        values[index] = value;
        return true;
      }
    }
    return false;
  }

  /**
   * Deletes a key-value mapping from the hashtable.
   *
   * @param key The key.
   */
  public V delete(K key) {
    if (key == null) {
      throw new IllegalArgumentException("The key must not be null.");
    }
    for (int i = 0; i < size; i++) {
      int index = hashCode(key, i);
      if (keys[index].equals(key)) {
        keys[index] = null;
        V result = values[index];
        values[index] = null;
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the value associated with the given key.
   *
   * @param key The input key.
   * @return Value mapped with the input key.
   */
  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException("The key must not be null.");
    }
    for (int i = 0; i < size; i++) {
      int index = hashCode(key, i);
      if (keys[index].equals(key)) {
        return values[index];
      }
    }
    return null;
  }

  /**
   * Checks if the input key is stored in the table.
   *
   * @param key The key.
   * @return True if the key is in the table.
   */
  public boolean containsKey(K key) {
    for (int i = 0; i < size; i++) {
      int index = hashCode(key, i);
      if (keys[index].equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Calculates the double hash of the key.
   *
   * @param key The key to be stored in the hashtable.
   * @param i The index.
   * @return The hash value.
   */
  private int hashCode(Object key, int i) {
    int k = Math.abs(key.hashCode());
    int hashCode1 = k % 701;
    int hashCode2 = 1 + (k % 700);
    int hashCode = (hashCode1 + i * hashCode2) % 701;
    return hashCode % size;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < keys.length; i++) {
      if (keys[i] == null) {
        continue;
      }
      sb.append(keys[i] + "=" + values[i] + ", ");
    }
    return sb.replace(sb.length() - 2, sb.length(), "]").toString();
  }
}
