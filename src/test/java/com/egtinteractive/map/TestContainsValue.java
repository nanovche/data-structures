package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.fillMapWithNullKeyAndNullValues;
import static com.egtinteractive.TestUtilities.fillMapWithoutNulls;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestContainsValue {

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class)
  public void testContainsValueNullToNullKey(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithoutNulls(map, capacity);

    map.put(null, null);

    assertTrue(map.containsValue(null));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class)
  public void testContainsValueNullToNonNullKey(CustomMap<String, Integer> map,
      final int capacity) {

    fillMapWithoutNulls(map, capacity);

    String key = UUID.randomUUID().toString();

    map.put(key, null);

    assertTrue(map.containsValue(null));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsValueNullFalse(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithoutNulls(map, capacity);

    assertFalse(map.containsValue(null));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsValue(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);
    String key = UUID.randomUUID().toString();
    Integer value = new Random().nextInt();

    map.put(key, value);

    assertTrue(map.containsValue(value));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class)
  public void testContainsValueFalse(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    Integer value = new Random().nextInt();

    assertFalse(map.containsValue(value));

  }

}
