package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.FIVE_INDEX;
import static com.egtinteractive.TestUtilities.NINE_INDEX;
import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static com.egtinteractive.TestUtilities.loadListWithData;
import static com.egtinteractive.TestUtilities.loadListWithDataNoNulls;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestGet {

  @Test(dataProvider = "dataProviderForGet", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testGet(final CustomList<String> list, final int capacity, final int index) {

    loadListWithData(list, capacity);

    String retrievedElement = list.get(index);

    assertEquals(retrievedElement, list.get(index));
  }

  @Test(dataProvider = "dataProviderForGetArrayList", dataProviderClass = CommonDataProviders.class, expectedExceptions = IndexOutOfBoundsException.class)
  public void testGetNotFilledEntirely(final CustomList<String> list, final int capacity) {

    loadListWithDataNoNulls(list, capacity);

    final int index = generateRandomNumberWithinRange(FIVE_INDEX, NINE_INDEX);

    String retrievedElement = list.get(index);

  }

  @Test(expectedExceptions = IndexOutOfBoundsException.class, dataProvider = "dataProviderIndexOutOfBounds", dataProviderClass = CommonDataProviders.class,
      invocationCount = 10)
  public void testGetIndexOutOfBounds(final CustomList<String> list, final int capacity,
      final int index) {

    loadListWithData(list, capacity);

    String retrievedElement = list.get(index);

  }

  @Test(dataProvider = "dataProviderIndexOutOfBoundsZeroIndexZeroCapacity", dataProviderClass = CommonDataProviders.class,
      expectedExceptions = IndexOutOfBoundsException.class, invocationCount = 10)
  public void testGetIndexOutOfBoundsZeroIndexZeroCapacity(final CustomList<String> list,
      final int capacity, final int index) {

    loadListWithData(list, capacity);

    String retrievedElement = list.get(index);

  }

}
