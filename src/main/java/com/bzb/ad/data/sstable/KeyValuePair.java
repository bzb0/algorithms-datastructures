package com.bzb.ad.data.sstable;

import java.io.Serializable;
import java.util.Objects;

/**
 * A wrapper class for storing a key-value pair. The key is used for sorting the document/object in the SSTable.
 */
public class KeyValuePair implements Serializable {

  private String key;
  private Object value;

  public KeyValuePair() {
  }

  public KeyValuePair(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyValuePair that = (KeyValuePair) o;
    return Objects.equals(key, that.key) && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    return "KeyValuePair{" +
        "key='" + key + '\'' +
        ", value=" + value +
        '}';
  }
}
