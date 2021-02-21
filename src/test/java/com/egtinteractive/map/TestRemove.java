package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.fillMapWithNullKeyAndNullValues;
import static com.egtinteractive.TestUtilities.fillMapWithoutNulls;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Random;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestRemove {

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class)
  public void testRemoveNullEntryEmptyMap(CustomMap<String, Integer> map,
      final int capacity) {

    String string = UUID.randomUUID().toString();

    assertNull(map.remove(string));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class)
  public void testRemoveNullKey(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithoutNulls(map, capacity);

    Integer integer = new Random().nextInt();
    map.put(null, integer);
    assertEquals(map.remove(null), integer);

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemoveNullKeyNotPresent(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithoutNulls(map, capacity);

    assertNull(map.remove(null));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemoveKey(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    String string = UUID.randomUUID().toString();
    Integer integer = new Random().nextInt();
    map.put(string, integer);
    assertEquals(map.remove(string), integer);

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemoveKeyNotPresent(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    String string = UUID.randomUUID().toString();

    assertNull(map.remove(string));

  }

}
