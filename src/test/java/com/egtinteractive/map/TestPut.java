package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.fillMapWithNullKey;
import static com.egtinteractive.TestUtilities.fillMapWithNullKeyAndNullValues;
import static com.egtinteractive.TestUtilities.fillMapWithoutNulls;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestPut {

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testPutWithoutNulls(CustomMap<String, Integer> map, final int capacity) {

    for (int i = 0; i <= capacity; i++) {
      String key = UUID.randomUUID().toString();
      Integer value = new Random().nextInt();
      assertNull(map.put(key, value));
      assertTrue(map.containsKey(key));
      assertTrue(map.containsValue(value));
      assertEquals(value, map.get(key));
    }

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testPutWithNullValues(CustomMap<String, Integer> map, final int capacity) {

    for (int i = 0; i <= capacity; i++) {
      String key = UUID.randomUUID().toString();
      int value = new Random().nextInt();
      map.put(key, i % 2 == 0 ? value : null);
      assertTrue(map.containsValue(i % 2 == 0 ? value : null));
      assertEquals(i % 2 == 0 ? value : null, map.get(key));
    }

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testPutWithSameKey(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    String key = UUID.randomUUID().toString();
    Integer firstValue = new Random().nextInt();
    Integer secondValue = new Random().nextInt();

    map.put(key, firstValue);
    assertEquals(firstValue, map.put(key, secondValue));
    assertEquals(secondValue, map.get(key));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testPutNullKeyWhenIsNotPresent(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithoutNulls(map, capacity);

    Integer value = new Random().nextInt();
    map.put(null, value);
    assertTrue(map.containsKey(null));
    assertEquals(value, map.get(null));
  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testPutNullKeyWhenIsPresent(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKey(map, capacity);

    Integer currentValue = new Random().nextInt();
    Integer returnedValue = map.put(null, currentValue);
    assertTrue(map.containsKey(null));
    assertNotEquals(returnedValue, map.get(null));
    assertEquals(currentValue, map.get(null));

  }

}
