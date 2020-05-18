package com.bzb.ad.data.sstable;

/**
 * Signals that an I/O error of some sort has occurred, while writing to or reading from the SSTable file.
 */
public class SSTableIOException extends RuntimeException {

  public SSTableIOException(String message) {
    super(message);
  }

  public SSTableIOException(String message, Throwable cause) {
    super(message, cause);
  }
}
