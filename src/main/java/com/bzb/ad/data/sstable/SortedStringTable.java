package com.bzb.ad.data.sstable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A simple implementation of a sorted string table (SSTable).
 */
public class SortedStringTable {

  /* The max. table size in bytes (long number). */
  public static final int MAX_TABLE_SIZE_BYTES = 8;
  /* The max. document size in bytes (long number). */
  public static final int MAX_DOCUMENT_SIZE_BYTES = 8;
  /* The document separator. */
  public static final char DOCUMENT_SEPARATOR = 0x1E;

  /* The file channel used for reading/writing the SSTable file. */
  private final FileChannel channel;
  /* The size of the SSTable. */
  private int size = 0;
  /* Is the SSTable mutable. */
  private boolean writable;
  /* The version of the SSTable. */
  private int version = 1;
  /* The start position of the lookup table. */
  private int lookupTableStart = 0;
  /* The lookup table. Holds the positions of the first character of a key of a document. */
  private Map<String, Integer> lookupTable = new HashMap<>();

  /**
   * Creates a SSTable.
   *
   * @param path Path to the SSTable file. If the file doesn't exist a new file will be created.
   */
  public SortedStringTable(Path path) {
    if (!Files.exists(path)) {
      try {
        this.channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);
        writeHeader();
        writable = true;
      } catch (IOException e) {
        throw new SSTableIOException("Error initializing the SSTable file.", e);
      }
    } else {
      try {
        this.channel = FileChannel.open(path, StandardOpenOption.READ);
        size = (int) channel.size();
        writable = false;
        loadHeader();
        // Loading the lookup table if the SSTable is not empty
        if (lookupTableStart > MAX_TABLE_SIZE_BYTES + 2) {
          loadLookupTable();
        }
      } catch (IOException e) {
        throw new SSTableIOException("Error reading the SSTable file.", e);
      }
    }
  }

  /**
   * Sorts and writes the list of documents in the SSTable.
   *
   * @param documents List of documents.
   */
  public void writeDocuments(List<KeyValuePair> documents) {
    if (!writable) {
      throw new UnmodifiableSSTableException("Can't write new documents to a non-mutable SSTable.");
    }

    // Sorting the list of documents by key
    List<KeyValuePair> sortedDocuments = new ArrayList<>(documents);
    sortedDocuments.sort(Comparator.comparing(KeyValuePair::getKey));

    writeSortedDocuments(sortedDocuments);

    try {
      // Writing the lookup table at the end of the document
      channel.write(ByteBuffer.wrap(convertToByteArray(lookupTable)));

      // Writing the position of the lookup table in the header
      ByteBuffer sizeBuffer = ByteBuffer.allocate(10);
      sizeBuffer.order(ByteOrder.LITTLE_ENDIAN);
      sizeBuffer.putInt(size);
      sizeBuffer.flip();
      channel.position(2);
      channel.write(sizeBuffer);
      writable = false;
    } catch (IOException e) {
      throw new SSTableIOException("Error writing the lookup table.", e);
    }
  }

  /**
   * Searches for a document based on its key.
   *
   * @param key The key of the document.
   * @return An {@link Optional} holding the found document, or {@link Optional#empty()} if the document wasn't be found.
   */
  public Optional<KeyValuePair> findByKey(String key) {
    if (key == null || key.isEmpty()) {
      throw new IllegalArgumentException("Key must not be NULL or empty.");
    }
    String key0 = String.valueOf(key.charAt(0));
    if (!lookupTable.containsKey(key0)) {
      return Optional.empty();
    }
    return Optional.ofNullable(findDocument(lookupTable.get(key0), key));
  }

  /**
   * Returns the size of the SSTable file.
   *
   * @return The size of the underlying file.
   */
  public int getSize() {
    return size;
  }

  /**
   * Returns the version of the SSTable file.
   *
   * @return The version of the SSTable file.
   */
  public int getVersion() {
    return version;
  }

  /**
   * Closes the SSTable file.
   */
  public void close() {
    try {
      channel.close();
    } catch (IOException e) {
      throw new SSTableIOException("Error closing the SSTable file.", e);
    }
  }

  /**
   * Writes a list of documents (KeyValuePair) in the SSTable.
   *
   * @param documents A sorted list of documents ({@link KeyValuePair}).
   */
  private void writeSortedDocuments(List<KeyValuePair> documents) {
    for (KeyValuePair document : documents) {
      String key0 = String.valueOf(document.getKey().charAt(0));
      // Storing the offset for the documents, having the first key character 'X'
      if (!lookupTable.containsKey(key0)) {
        lookupTable.put(key0, size);
      }

      // Creating the byte representation of the document
      ByteBuffer documentBuffer = createDocument(document);
      writeByteBuffer(documentBuffer);
      size += documentBuffer.capacity();
    }
  }

  /**
   * Creates a byte representation of an input document.
   *
   * @param document The object/document to be written.
   * @return A {@link ByteBuffer} containing metadata (data length and document separator) and the byte representation of the input document.
   */
  private ByteBuffer createDocument(KeyValuePair document) {
    byte[] docBytes = convertToByteArray(document);

    ByteBuffer documentBuffer = ByteBuffer.allocate(MAX_DOCUMENT_SIZE_BYTES + docBytes.length + 1);
    documentBuffer.order(ByteOrder.LITTLE_ENDIAN);
    documentBuffer.putInt(docBytes.length + 1);
    documentBuffer.put(new byte[4]);
    documentBuffer.put(docBytes);
    documentBuffer.put((byte) DOCUMENT_SEPARATOR);
    documentBuffer.flip();
    return documentBuffer;
  }

  /**
   * Searches for a document starting from a given offset.
   *
   * @param offset The starting offset.
   * @param key    The key associated with the document.
   * @return The retrieved document, or <code>null</code> if the document with the given key wasn't found.
   */
  private KeyValuePair findDocument(int offset, String key) {
    while (true) {
      byte[] docBytes = readItem(offset);
      try {
        KeyValuePair document = convertToObject(docBytes);
        if (document.getKey().equals(key)) {
          return document;
        }
        offset += docBytes.length + MAX_DOCUMENT_SIZE_BYTES + 1;
      } catch (SSTableIOException e) {
        // We have reached the lookup table.
        return null;
      }
    }
  }

  /**
   * Loads the lookup table from the SSTable file.
   */
  private void loadLookupTable() {
    try {
      ByteBuffer buffer = ByteBuffer.allocate(size - lookupTableStart);
      channel.read(buffer, size - buffer.capacity());
      lookupTable = convertToObject(buffer.array());
    } catch (IOException e) {
      throw new SSTableIOException("Error loading the lookup table.", e);
    }
  }

  /**
   * Loads the SSTable header.
   */
  private void loadHeader() {
    try {
      ByteBuffer versionBuffer = ByteBuffer.allocate(2);
      versionBuffer.order(ByteOrder.LITTLE_ENDIAN);
      channel.read(versionBuffer);
      version = convertToInteger(versionBuffer.array());

      ByteBuffer offsetBuffer = ByteBuffer.allocate(MAX_TABLE_SIZE_BYTES);
      channel.read(offsetBuffer);
      lookupTableStart = convertToInteger(offsetBuffer.array());
    } catch (IOException e) {
      throw new SSTableIOException("Error loading the SSTable header.", e);
    }
  }

  /**
   * Writes the header (version and lookup table position) in the SSTable file.
   */
  private void writeHeader() {
    // Writing the SSTable version (first 2 bytes)
    ByteBuffer versionBuffer = ByteBuffer.allocate(2);
    versionBuffer.order(ByteOrder.LITTLE_ENDIAN);
    versionBuffer.putShort((short) version);
    versionBuffer.flip();
    writeByteBuffer(versionBuffer);

    // Reserving the next 8 bytes for the location of the lookup table
    ByteBuffer maxSizeBuffer = ByteBuffer.allocate(MAX_TABLE_SIZE_BYTES);
    maxSizeBuffer.order(ByteOrder.LITTLE_ENDIAN);
    maxSizeBuffer.position(MAX_TABLE_SIZE_BYTES);
    maxSizeBuffer.flip();
    writeByteBuffer(maxSizeBuffer);

    size += MAX_TABLE_SIZE_BYTES + 2;
  }

  /**
   * Reads a document from a given starting offset.
   *
   * @param start The document offset in the file.
   * @return A byte array containing the document.
   */
  private byte[] readItem(int start) {
    byte[] docSizeByteArray = read(start, MAX_DOCUMENT_SIZE_BYTES);
    int documentSize = convertToInteger(docSizeByteArray);
    return read(start + MAX_DOCUMENT_SIZE_BYTES, documentSize - 1);
  }

  /**
   * Reads {@code length} bytes from the given {@code start} position in the SSTable file.
   *
   * @param start  The starting offset.
   * @param length The number of bytes to be read.
   * @return The read bytes.
   */
  private byte[] read(int start, int length) {
    try {
      ByteBuffer buffer = ByteBuffer.allocate(length);
      buffer.order(ByteOrder.LITTLE_ENDIAN);
      channel.read(buffer, start);
      return buffer.array();
    } catch (IOException e) {
      throw new SSTableIOException("Error reading data from the SSTable file.", e);
    }
  }

  private void writeByteBuffer(ByteBuffer buffer) {
    try {
      channel.write(buffer);
    } catch (IOException e) {
      throw new SSTableIOException("Error writing data to the SSTable file.", e);
    }
  }

  private <T> byte[] convertToByteArray(T object) {
    try (ByteArrayOutputStream bytesOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bytesOutputStream)) {
      objectOutputStream.writeObject(object);
      objectOutputStream.flush();
      return bytesOutputStream.toByteArray();
    } catch (IOException e) {
      throw new SSTableIOException("Error converting object to a byte array.", e);
    }
  }

  private <T> T convertToObject(byte[] bytes) {
    try (ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream)) {
      return (T) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new SSTableIOException("Error converting byte array to an object.", e);
    }
  }

  private int convertToInteger(byte[] bytes) {
    int result = (bytes[0] & 0xFF);
    for (int i = 1, j = 8; i < bytes.length; i++, j += 8) {
      result |= (bytes[i] & 0xFF) << j;
    }
    return result;
  }
}
