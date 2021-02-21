package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.MAX_CAPACITY;
import static com.egtinteractive.TestUtilities.ZERO_CAPACITY;
import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertEquals;

import java.util.UUID;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestHashCode {


  @DataProvider(name = "dataProviderHashCodeEqualObjectsEqualHashCodes")
  public Object[][] dataProviderForHashCodeEqualObjectsEqualHashCodes() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    final CustomList<String> arrayList = new CustomArrayList<>(capacity);
    final CustomList<String> otherArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> linkedList = new CustomLinkedList<>();
    final CustomList<String> otherLinkedList = new CustomLinkedList<>();

    return new Object[][]{{arrayList, otherArrayList, capacity},
        {linkedList, otherLinkedList, capacity}};

  }

  @Test(dataProvider = "dataProviderHashCodeEqualObjectsEqualHashCodes", invocationCount = 10)
  public void testHashCodeEqualObjectsEqualHashCodes(final CustomList<String> list,
      final CustomList<String> otherList, final int capacity) {

    for (int i = 0; i < capacity; i++) {
      String string = UUID.randomUUID().toString();
      list.add(string);
      otherList.add(string);
    }

    assertEquals(otherList, list);
    assertEquals(list.hashCode(), otherList.hashCode());

  }

  @Test(dataProvider = "dataProviderCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testHashCodeAlwaysTheSame(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    int hashcode = list.hashCode();

    for (int i = 0; i < generateRandomNumberWithinRange(0, Integer.MAX_VALUE); i++) {
      assertEquals(hashcode, list.hashCode());
    }

  }

}
