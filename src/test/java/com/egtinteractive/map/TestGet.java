package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.fillMapWithNullKeyAndNullValues;
import static com.egtinteractive.TestUtilities.fillMapWithoutNulls;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Random;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestGet {

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testGetWithNullKey(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithoutNulls(map, capacity);

    Integer integer = new Random().nextInt();

    map.put(null, integer);
    assertEquals(map.get(null), integer);

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testGet(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    String string = UUID.randomUUID().toString();
    Integer integer = new Random().nextInt();

    map.put(string, integer);
    assertEquals(map.get(string), integer);

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testGetNullEntry(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    String string = UUID.randomUUID().toString();

    assertNull(map.get(string));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testGetNullEntryEmptyMap(CustomMap<String, Integer> map, final int capacity) {

    String string = UUID.randomUUID().toString();

    assertNull(map.get(string));

  }

}
