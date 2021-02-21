package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.MAX_CAPACITY;
import static com.egtinteractive.TestUtilities.ONE_CAPACITY;
import static com.egtinteractive.TestUtilities.ZERO_CAPACITY;
import static com.egtinteractive.TestUtilities.fillMapWithoutNulls;
import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestEquals {

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testEqualsSameReference(final CustomMap<String, Integer> map, final int capacity) {

    assertEquals(map, map);

  }

  @DataProvider(name = "providerCustomAndLibrary")
  public Object[][] dataProviderCustomAndLibrary() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    CustomMap<String, Integer> map = new CustomHashMap<>(capacity);
    Map<String, Integer> secondMap = new LinkedHashMap<>(capacity);
    return new Object[][]{{map, secondMap}};

  }

  @Test(dataProvider = "providerCustomAndLibrary", invocationCount = 10)
  public void testEqualsOtherType(final CustomMap<String, Integer> map, final Map<String, Integer> secondMap) {

    assertNotEquals(map, secondMap);

  }

  @DataProvider(name = "providerDifferentSizes")
  public Object[][] dataProviderDifferentSizes() {

    final int firstMapCapacity = MAX_CAPACITY;
    final int secondMapCapacity = firstMapCapacity - 5;

    CustomMap<String, Integer> map = new CustomHashMap<>(MAX_CAPACITY);
    CustomMap<String, Integer> secondMap = new CustomHashMap<>(secondMapCapacity);

    return new Object[][]{{map, firstMapCapacity, secondMap, secondMapCapacity}};

  }

  @Test(dataProvider = "providerDifferentSizes")
  public void testEqualsDifferentSizes(final CustomMap<String, Integer> map, final int firstMapCapacity,
      final CustomMap<String, Integer> secondMap, final int secondMapCapacity) {

    fillMapWithoutNulls(map, firstMapCapacity);
    fillMapWithoutNulls(secondMap, secondMapCapacity);

    assertNotEquals(map, secondMap);

  }

  @DataProvider(name = "providerBothZeroCapacity")
  public Object[][] dataProviderBothZeroCapacity() {

    final int capacity = ZERO_CAPACITY;

    CustomMap<String, Integer> map = new CustomHashMap<>(capacity);
    CustomMap<String, Integer> secondMap = new CustomHashMap<>(capacity);

    return new Object[][]{{map, secondMap}};

  }

  @Test(dataProvider = "providerBothZeroCapacity")
  public void testEqualsBothEmpty(final CustomMap<String, Integer> map,
      final CustomMap<String, Integer> secondMap) {

    boolean res = map.equals(secondMap);
    assertTrue(res);

  }

  @DataProvider(name = "providerDifferentContent")
  public Object[][] dataProviderDifferentContent() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);

    CustomMap<String, Integer> map = new CustomHashMap<>(capacity);
    CustomMap<String, Integer> secondMap = new CustomHashMap<>(capacity);

    return new Object[][]{{map, secondMap, capacity}};

  }

  @Test(dataProvider = "providerDifferentContent", invocationCount = 10)
  public void testEqualsDifferentContent(final CustomMap<String, Integer> map,
      final CustomMap<String, Integer> secondMap, final int capacity) {

    fillMapWithoutNulls(map, capacity);
    fillMapWithoutNulls(secondMap, capacity);

    assertNotEquals(map, secondMap);

  }

  @DataProvider(name = "providerSameContent")
  public Object[][] dataProviderSameContent() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);

    CustomMap<String, Integer> map = new CustomHashMap<>(capacity);
    CustomMap<String, Integer> secondMap = new CustomHashMap<>(capacity);

    for (int i = 0; i < capacity; i++) {
      String string = UUID.randomUUID().toString();
      Integer integer = new Random().nextInt();
      map.put(string, integer);
      secondMap.put(string, integer);
    }

    return new Object[][]{{map, secondMap}, {secondMap, map}};

  }

  @Test(dataProvider = "providerSameContent", invocationCount = 10)
  public void testEqualsSameContent(final CustomMap<String, Integer> map,
      final CustomMap<String, Integer> secondMap) {

    assertEquals(map, secondMap);
    assertEquals(secondMap, map);
  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testEqualsNullProperty(final CustomMap<String, Integer> map, final int capacity) {

    assertNotEquals(map, null);

  }

  @DataProvider(name = "providerTransitiveProperty")
  public Object[][] dataProviderTransitiveProperty() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);

    CustomMap<String, Integer> map = new CustomHashMap<>(capacity);
    CustomMap<String, Integer> secondMap = new CustomHashMap<>(capacity);
    CustomMap<String, Integer> thirdMap = new CustomHashMap<>(capacity);

    return new Object[][]{{map, secondMap, thirdMap, capacity}};

  }

  @Test(dataProvider = "providerTransitiveProperty", invocationCount = 10)
  public void testEqualsTransitiveProperty(final CustomMap<String, Integer> map,
      final CustomMap<String, Integer> secondMap, final CustomMap<String, Integer> thirdMap,
      final int capacity) {

    for (int i = 0; i < capacity; i++) {
      String string = UUID.randomUUID().toString();
      Integer integer = new Random().nextInt();
      map.put(string, integer);
      secondMap.put(string, integer);
      thirdMap.put(string, integer);
    }

    assertEquals(map, secondMap);
    assertEquals(secondMap, thirdMap);
    assertEquals(map, thirdMap);

  }

}
