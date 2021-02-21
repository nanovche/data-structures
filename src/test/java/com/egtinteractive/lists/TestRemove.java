package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import org.testng.annotations.Test;

public class TestRemove {

  @Test(dataProvider = "dataProviderOneMinCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemoveElement(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);
    String randomElement = list.get(generateRandomNumberWithinRange(0, capacity - 1));

    if (randomElement != null) {
      assertTrue(list.remove(randomElement));
      assertFalse(list.contains(randomElement));
    } else {
      assertTrue(list.remove(randomElement));
    }

  }

  @Test(dataProvider = "dataProviderCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemoveElementFalse(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);
    String randomString = UUID.randomUUID().toString();

    assertFalse(list.remove(randomString));
  }

  @Test(dataProvider = "dataProviderLinkedList", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemoveElemenLinkedListHeadNull(final CustomList<String> list,
      final int capacity) {

    String randomString = UUID.randomUUID().toString();

    assertFalse(list.remove(randomString));
  }

  @Test(dataProvider = "dataProviderCapacityOnePlusIndex", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemoveIndex(final CustomList<String> list, final int capacity, final int index) {

    loadListWithData(list, capacity);

    String removedElement = list.remove(index);

    if (removedElement != null) {
      assertFalse(list.contains(removedElement));
    } else {
      assertNull(removedElement);
    }

  }

  @Test(dataProvider = "dataProviderLinkedListZeroIndex", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemoveIndexLinkedListIndexZero(final CustomList<String> list, final int capacity,
      final int index) {

    loadListWithData(list, capacity);

    String removedElement = list.remove(index);

    assertFalse(list.contains(removedElement));
  }

  @Test(dataProvider = "dataProviderIndexOutOfBounds", dataProviderClass = CommonDataProviders.class,
      expectedExceptions = IndexOutOfBoundsException.class, invocationCount = 10)
  public void testRemoveIndexOutOfBounds(final CustomList<String> list, final int capacity,
      final int index) {

    loadListWithData(list, capacity);
    String removedElement = list.remove(index);

  }

  @Test(dataProvider = "dataProviderIndexOutOfBoundsZeroIndexZeroCapacity", dataProviderClass = CommonDataProviders.class,
      expectedExceptions = IndexOutOfBoundsException.class, invocationCount = 10)
  public void testRemoveIndexOutOfBoundsZeroCapacityZeroIndex(final CustomList<String> list,
      final int capacity, final int index) {

    loadListWithData(list, capacity);
    String removedElement = list.remove(index);

  }

}
