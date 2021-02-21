package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.ZERO_CAPACITY;
import static com.egtinteractive.TestUtilities.fillMapWithNullKeyAndNullValues;
import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestHashCode {

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testHashCodeSameEveryInvocation(final CustomMap<String, Integer> map,
      final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    int hashCode = map.hashCode();

    for (int i = 0; i < generateRandomNumberWithinRange(ZERO_CAPACITY, Integer.MAX_VALUE); i++) {
      assertEquals(hashCode, map.hashCode());
    }

  }

  @Test(dataProvider = "providerSameContent", dataProviderClass = TestEquals.class, invocationCount = 10)
  public void testHashCodeEqualObjectsEqualHashCodes(final CustomMap<String, Integer> map,
      final CustomMap<String, Integer> secondMap) {

    assertEquals(map.hashCode(), secondMap.hashCode());

  }

}
