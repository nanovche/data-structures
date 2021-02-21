package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.fillMapWithNullKeyAndNullValues;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import com.egtinteractive.TestUtilities;
import java.util.Random;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestContainsKey {

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsNullKey(CustomMap<String, Integer> map, final int capacity) {

    TestUtilities.fillMapWithoutNulls(map, capacity);

    Integer integer = new Random().nextInt();
    map.put(null, integer);
    assertTrue(map.containsKey(null));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsNullKeyFalse(CustomMap<String, Integer> map, final int capacity) {

    TestUtilities.fillMapWithoutNulls(map, capacity);

    assertFalse(map.containsKey(null));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContains(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    String string = UUID.randomUUID().toString();
    Integer integer = new Random().nextInt();
    map.put(string, integer);
    assertTrue(map.containsKey(string));

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsFalse(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    String string = UUID.randomUUID().toString();

    assertFalse(map.containsKey(string));

  }

  @Test(dataProvider = "providerNotZeroCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testContainsKeyNullEntryEmptyMap(CustomMap<String, Integer> map, final int capacity) {

    String string = UUID.randomUUID().toString();

    assertFalse(map.containsKey(string));

  }

  @Test(dataProvider = "providerZeroCapacity", dataProviderClass = CommonDataProviders.class)
  public void testContainsKeyZeroCapacity(CustomMap<String, Integer> map, final int capacity) {

    String string = UUID.randomUUID().toString();

    assertFalse(map.containsKey(string));

  }

}
