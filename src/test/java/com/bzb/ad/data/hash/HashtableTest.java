package com.bzb.ad.data.hash;

import org.junit.Assert;
import org.junit.Test;

public class HashtableTest {

  @Test
  public void add_addsElementsToHashTable() {
    Hashtable<Object, Object> hashtable = new Hashtable<>();

    hashtable.add("Test", 123);
    hashtable.add(2, 20.0d);
    hashtable.add("Hashtable", "XYZ");

    Assert.assertEquals(true, hashtable.containsKey("Test"));
    Assert.assertEquals(true, hashtable.containsKey("Hashtable"));
    Assert.assertEquals(true, hashtable.containsKey(2));

    Assert.assertEquals(123, hashtable.get("Test"));
    Assert.assertEquals("XYZ", hashtable.get("Hashtable"));
    Assert.assertEquals(20.d, hashtable.get(2));
  }
}
