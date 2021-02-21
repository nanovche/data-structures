package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.ZER0_INDEX;
import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import org.testng.annotations.Test;

public class TestContains {

  @Test(dataProvider = "dataProviderTwoMinCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsTrue(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    String string = list.get(generateRandomNumberWithinRange(ZER0_INDEX, capacity - 1));

    assertTrue(list.contains(string));
    assertTrue(list.contains(null));
  }

  @Test(dataProvider = "dataProviderCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsFalse(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    String string = UUID.randomUUID().toString();

    assertFalse(list.contains(string));

  }

  @Test(dataProvider = "dataProviderLinkedList", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsLinkedListHeadNull(final CustomLinkedList<String> list,
      final int... capacity) {

    String string = UUID.randomUUID().toString();

    assertFalse(list.contains(string));

  }

}
