package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.fillMapWithoutNulls;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestSize {

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testSize(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithoutNulls(map, capacity);

    assertEquals(map.size(), capacity + 1);

    map.put("Martin", null);

    assertEquals(map.size(), capacity + 2);

    map.remove("Martin");

    assertEquals(map.size(), capacity + 1);

    map.put(null, null);

    assertEquals(map.size(), capacity + 2);

    map.remove(null);

    assertEquals(map.size(), capacity + 1);
  }


}



