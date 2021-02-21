package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertEquals;

import java.util.UUID;
import org.testng.annotations.Test;

public class TestSize {

  @Test(dataProvider = "dataProviderCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testSize(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    assertEquals(list.size(), capacity);

    list.add(UUID.randomUUID().toString());

    assertEquals(list.size(), capacity + 1);

    list.remove(generateRandomNumberWithinRange(0, capacity - 1));

    assertEquals(list.size(), capacity);

  }

}
