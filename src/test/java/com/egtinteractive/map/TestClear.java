package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.fillMapWithNullKeyAndNullValues;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestClear {


  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testClear(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    map.clear();

    assertEquals(map.size(), 0);

  }

}
