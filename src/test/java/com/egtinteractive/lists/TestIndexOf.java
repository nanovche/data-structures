package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import java.util.UUID;
import org.testng.annotations.Test;

public class TestIndexOf {

  @Test(dataProvider = "dataProviderTwoMinCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testIndexOf(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    String element = list.get(generateRandomNumberWithinRange(0, capacity - 1));

    int index = list.indexOf(element);
    int indexNull = list.indexOf(null);

    assertEquals(list.get(index), element);
    assertNull(list.get(indexNull));

  }

  @Test(dataProvider = "dataProviderArrayList", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testIndexOfNotFoundArrayList(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    String element = UUID.randomUUID().toString();

    assertEquals(-1, list.indexOf(element));

  }

  @Test(dataProvider = "dataProviderLinkedList", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testIndexOfNotFoundLinkedList(final CustomList<String> list,
      final int capacity) {

    loadListWithData(list, capacity);

    String element = UUID.randomUUID().toString();

    assertEquals(list.indexOf(element), -1);

  }

}
