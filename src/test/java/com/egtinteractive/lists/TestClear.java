package com.egtinteractive.lists;


import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestClear {

  @Test(dataProvider = "dataProviderLinkedList", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testClearLinkedList(final CustomLinkedList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    list.clear();

    assertEquals(0, list.size());
  }

  @Test(dataProvider = "dataProviderArrayList", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testClearArraylist(final CustomArrayList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    list.clear();

    assertEquals(0, list.size());

  }
}
