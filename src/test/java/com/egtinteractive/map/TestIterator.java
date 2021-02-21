package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.fillMapWithNullKeyAndNullValues;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import com.egtinteractive.lists.CustomList;
import com.egtinteractive.map.CustomHashMap.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestIterator {


  @Test(dataProvider = "providerZeroCapacity", dataProviderClass = CommonDataProviders.class)
  public void testIteratorHasNextEmptyMap(CustomMap<String, Integer> map, final int capacity) {

    Iterator<Entry<String, Integer>> itr = map.iterator();
    assertFalse(itr.hasNext());

  }

  @Test(dataProvider = "providerZeroCapacity", dataProviderClass = CommonDataProviders.class,
      expectedExceptions = NoSuchElementException.class)
  public void testIteratorNextEmptyMap(CustomMap<String, Integer> map, final int capacity) {

    Iterator<Entry<String, Integer>> itr = map.iterator();

    itr.next();

  }

  @Test(dataProvider = "providerNotZeroCapacity", dataProviderClass = CommonDataProviders.class)
  public void testIteratorhasNextAndNext(CustomMap<String, Integer> map, final int capacity) {

    fillMapWithNullKeyAndNullValues(map, capacity);

    Iterator<Entry<String, Integer>> itr = map.iterator();

    for (int i = 0; i < capacity; i++) {
      assertTrue(itr.hasNext());
      assertNotNull(itr.next());
    }

  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class)
  public void testNextAndHasNextOnOneItem(CustomMap<String, Integer> map, final int capacity) {

    String string = UUID.randomUUID().toString();
    Integer integer = new Random().nextInt();

    map.put(string, integer);

    Iterator<Entry<String, Integer>> itr = map.iterator();

    assertTrue(itr.hasNext());

    Entry<String, Integer> entry = itr.next();
    assertEquals(entry.getKey(), string);
    assertEquals(entry.getValue(), integer);

    assertFalse(itr.hasNext());
  }

  @Test(dataProvider = "provider", dataProviderClass = CommonDataProviders.class)
  public void testNextAndHasNextOnOneItemNull(CustomMap<String, Integer> map, final int capacity) {

    map.put(null, null);

    Iterator<Entry<String, Integer>> itr = map.iterator();

    assertTrue(itr.hasNext());

    Entry<String, Integer> entry = itr.next();
    assertNull(entry.getKey(), null);
    assertNull(entry.getValue(), null);

    assertFalse(itr.hasNext());
  }

  @Test(dataProvider = "provider", dataProviderClass = com.egtinteractive.map.CommonDataProviders.class, expectedExceptions = IllegalStateException.class)
  public void testRemoveOnEmptyList(final CustomMap<String, Integer> map, final int capacity) {

    Iterator<Entry<String, Integer>> mapIterator = map.iterator();
    mapIterator.remove();

  }

  @Test(dataProvider = "provider", dataProviderClass = com.egtinteractive.map.CommonDataProviders.class, expectedExceptions = IllegalStateException.class)
  public void testRemoveCallMoreThanOneTimeInARow(final CustomMap<String, Integer> map, final int capacity) {

    String string = UUID.randomUUID().toString();
    int integer = new Random().nextInt();

    map.put(string, integer);

    Iterator<Entry<String, Integer>> mapIterator = map.iterator();

    if (mapIterator.hasNext()) {
      mapIterator.next();
      mapIterator.remove();
    }

    mapIterator.remove();

  }

  @Test(dataProvider = "provider", dataProviderClass = com.egtinteractive.map.CommonDataProviders.class, invocationCount = 10)
  public void testRemove(final CustomMap<String, Integer> map, final int capacity) {

    Iterator<Entry<String, Integer>> mapIterator = map.iterator();

    while (mapIterator.hasNext()) {
      Entry<String, Integer> entry = mapIterator.next();
      mapIterator.remove();
      assertFalse(map.containsKey(entry.getKey()));
      assertFalse(map.containsValue(entry.getValue()));
    }

  }

}
