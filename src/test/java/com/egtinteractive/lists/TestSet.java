package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import java.util.UUID;
import org.testng.annotations.Test;

public class TestSet {

  @Test(dataProvider = "dataProviderCapacityOnePlusIndex", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testSet(final CustomList<String> list, final int capacity, final int index) {

    loadListWithData(list, capacity);

    String string = UUID.randomUUID().toString();

    list.set(index, string);
    assertEquals(string, list.get(index));
    list.set(index, null);
    assertNull(list.get(index));

  }

  @Test(dataProvider = "dataProviderIndexOutOfBounds", dataProviderClass = CommonDataProviders.class,
      expectedExceptions = IndexOutOfBoundsException.class, invocationCount = 10)
  public void testSetException(final CustomList<String> list, final int capacity, final int index) {

    loadListWithData(list, capacity);

    String string = UUID.randomUUID().toString();

    list.set(index, string);

  }

  @Test(dataProvider = "dataProviderIndexOutOfBoundsZeroIndexZeroCapacity", dataProviderClass = CommonDataProviders.class,
      expectedExceptions = IndexOutOfBoundsException.class, invocationCount = 10)
  public void testSetExceptionZeroIndexZeroCapacity(final CustomList<String> list,
      final int capacity,
      final int index) {

    loadListWithData(list, capacity);

    String string = UUID.randomUUID().toString();

    list.set(index, string);

  }

}
