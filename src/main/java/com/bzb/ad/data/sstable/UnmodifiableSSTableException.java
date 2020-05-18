package com.bzb.ad.data.sstable;

/**
 * Thrown when documents are written to a non-mutable SSTable.
 */
public class UnmodifiableSSTableException extends RuntimeException {

  public UnmodifiableSSTableException(String s) {
    super(s);
  }
}
