package com.bzb.ad.data.sstable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(Lifecycle.PER_CLASS)
public class SortedStringTableTest {

  private SortedStringTable sut;
  private final Path filePath = Paths.get("src", "test", "resources", "sstable");
  private final List<KeyValuePair> DOCUMENTS = Arrays.asList(
      new KeyValuePair("key1", "Object1234"),
      new KeyValuePair("ABC", Arrays.asList("Hello", "There")),
      new KeyValuePair("testXX", new LinkedList<>()),
      new KeyValuePair("ZZ001", new RuntimeException()),
      new KeyValuePair("key2", Arrays.asList("HAAA", new ArrayList<>())),
      new KeyValuePair("AMK", Arrays.asList("HAAA", Arrays.asList("ZZ", "TB", "ZZZ___"))),
      new KeyValuePair("z001", "__VCVCVC"),
      new KeyValuePair("LAST_ONE", ""));

  @BeforeAll
  public void setUp() {
    sut = new SortedStringTable(filePath);
    sut.writeDocuments(DOCUMENTS);
  }

  @AfterAll
  public void cleanUp() {
    sut.close();
    deleteTempFile(filePath);
  }

  @Test
  public void constructor_CreatesNonModifiableSSTable_ForValidFilePath() {
    // given
    Path path = Paths.get("src", "test", "resources", "testSSTable");
    SortedStringTable ssTable;

    // when
    ssTable = new SortedStringTable(path);

    // then
    Assertions.assertEquals(7024, ssTable.getSize());
    Assertions.assertEquals(1, ssTable.getVersion());
    Optional<KeyValuePair> charArray = ssTable.findByKey("ABC");
    Assertions.assertTrue(charArray.isPresent());
    Assertions.assertEquals(charArray.get(), new KeyValuePair("ABC", Arrays.asList("Hello", "There")));
    Optional<KeyValuePair> stringObj = ssTable.findByKey("1");
    Assertions.assertTrue(stringObj.isPresent());
    Assertions.assertEquals(stringObj.get(), new KeyValuePair("1", "Object1234"));
  }

  @Test
  public void writeDocuments_ThrowsUnmodifiableSSTableException_WhenTryingToWriteNewDocuments() {
    // given
    Path path = Paths.get("src", "test", "resources", "testSSTable");
    SortedStringTable ssTable;

    // when
    ssTable = new SortedStringTable(path);

    // then
    Assertions.assertThrows(UnmodifiableSSTableException.class, () -> ssTable.writeDocuments(Collections.emptyList()));
  }

  @Test
  public void writeDocuments_WritesAListOfDocumentsInAnEmptyFile_ForAValidInput() {
    SortedStringTable ssTable = null;
    Path path = Paths.get("src", "test", "resources", "tempTable");
    try {
      // given
      ssTable = new SortedStringTable(path);
      List<KeyValuePair> documents = Arrays.asList(new KeyValuePair("key1", "Object1234"), new KeyValuePair("ABC", Arrays.asList("Hello", "There")),
          new KeyValuePair("testXX", new LinkedList<>()), new KeyValuePair("ZZ001", new RuntimeException()));

      // when
      ssTable.writeDocuments(documents);

      // then
      Assertions.assertTrue(ssTable.findByKey("key1").isPresent());
      Assertions.assertTrue(ssTable.findByKey("ABC").isPresent());
      Assertions.assertTrue(ssTable.findByKey("testXX").isPresent());
      Assertions.assertTrue(ssTable.findByKey("ZZ001").isPresent());
    } finally {
      if (ssTable != null) {
        ssTable.close();
        deleteTempFile(path);
      }
    }
  }

  @ParameterizedTest
  @ValueSource(strings = {"key1", "key2"})
  public void findByKey_ReturnsTheAssociatedObject_ForAnExistingKey(String key) {
    Optional<KeyValuePair> result = sut.findByKey(key);
    Assertions.assertTrue(result.isPresent());
    // Assertions.assertEquals(DOCUMENTS.get(0), result.get());
  }

  @Test
  public void findByKey_ReturnsEmptyOptional_ForNonExistingKey() {
    Optional<KeyValuePair> result = sut.findByKey("UNKNOWN_KEY");
    Assertions.assertFalse(result.isPresent());
  }

  @Test
  public void findByKey_ThrowsIllegalArgumentException_ForNullOrEmptyKeys() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> sut.findByKey(null));
    Assertions.assertThrows(IllegalArgumentException.class, () -> sut.findByKey(""));
  }

  private void deleteTempFile(Path filePath) {
    filePath.toFile().delete();
  }
}
