package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.UUID;
import org.testng.annotations.Test;

public class TestAdd {

  @Test(dataProvider = "dataProviderCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testAdd(final CustomList<String> list, final int capacity) {

    for (int i = 0; i <= capacity; i++) {

      String string = UUID.randomUUID().toString();
      list.add(string);
      list.add(null);
      assertEquals(string, list.get(list.size() - 2));
      assertNull(list.get(list.size() - 1));

    }

  }

  @Test(dataProvider = "dataProviderCapacityPlusIndex", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testInsert(final CustomList<String> list, final int capacity, final int index) {

    loadListWithData(list, capacity);

    String string = UUID.randomUUID().toString();

    list.add(index, string);
    assertEquals(string, list.get(index));
    list.add(index, null);
    assertNull(list.get(index));

  }

  @Test(dataProvider = "dataProviderLinkedListZeroIndex", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testInsertAtZeroLinkedList(final CustomList<String> list, final int capacity,
      final int index) {

    loadListWithData(list, capacity);

    String string = UUID.randomUUID().toString();

    list.add(index, string);
    assertEquals(string, list.get(index));
    list.add(index, null);
    assertNull(list.get(index));

  }

  @Test(dataProvider = "dataProviderIndexOutOfBounds", dataProviderClass = CommonDataProviders.class,
      expectedExceptions = IndexOutOfBoundsException.class, invocationCount = 10)
  public void testInsertException(final CustomList<String> list, final int capacity,
      final int index) {

    loadListWithData(list, capacity);

    String string = UUID.randomUUID().toString();

    list.add(index, string);

  }

}
