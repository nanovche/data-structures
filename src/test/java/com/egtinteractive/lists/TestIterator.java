package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestIterator {

  @Test(dataProvider = "dataProviderEqualsSameReference", dataProviderClass = TestEquals.class, invocationCount = 10)
  public void testHasNextOnEmptyList(final CustomList<String> list) {

    Iterator<String> listIterator = list.iterator();

    assertFalse(listIterator.hasNext());

  }

  @Test(dataProvider = "dataProviderEqualsSameReference", dataProviderClass = TestEquals.class,
      expectedExceptions = NoSuchElementException.class, invocationCount = 10)
  public void testNextOnEmptyList(final CustomList<String> list) {

    Iterator<String> listIterator = list.iterator();

    listIterator.next();

  }

  @Test(dataProvider = "dataProviderOneMinCapacity", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testhasNextOnNonEmptyList(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    Iterator<String> listIterator = list.iterator();

    assertTrue(listIterator.hasNext());

  }

  @Test(dataProvider = "dataProviderEqualsSameReference", dataProviderClass = TestEquals.class, invocationCount = 10)
  public void testNextAndHasNextOnOneItem(final CustomList<String> list) {

    String string = UUID.randomUUID().toString();
    list.add(string);

    Iterator<String> listIterator = list.iterator();

    assertTrue(listIterator.hasNext());

    assertEquals(listIterator.next(), string);

    assertFalse(listIterator.hasNext());

  }

  @Test(dataProvider = "dataProviderEqualsSameReference", dataProviderClass = TestEquals.class, invocationCount = 10)
  public void testNextAndHasNextOnOneItemNull(final CustomList<String> list) {

    list.add(null);

    Iterator<String> listIterator = list.iterator();

    assertTrue(listIterator.hasNext());

    assertNull(listIterator.next());

    assertFalse(listIterator.hasNext());

  }

  @Test(dataProvider = "dataProviderIterator", dataProviderClass = CommonDataProviders.class, expectedExceptions = IllegalStateException.class)
  public void testRemoveOnEmptyList(final CustomList<String> list) {

    Iterator<String> listIterator = list.iterator();
    listIterator.remove();

  }

  @Test(dataProvider = "dataProviderIterator", dataProviderClass = CommonDataProviders.class, expectedExceptions = IllegalStateException.class)
  public void testRemoveCallMoreThanOneTimeInARow(final CustomList<String> list) {

    String string = UUID.randomUUID().toString();
    list.add(string);

    Iterator<String> listIterator = list.iterator();

    if (listIterator.hasNext()) {
      listIterator.next();
      listIterator.remove();
    }

    listIterator.remove();

  }

  @Test(dataProvider = "dataProviderIterator", dataProviderClass = CommonDataProviders.class, invocationCount = 10)
  public void testRemove(final CustomList<String> list) {

    List<String> strings = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      String string = UUID.randomUUID().toString();
      strings.add(string);
      list.add(string);
    }

    Iterator<String> listIterator = list.iterator();
    int index = 0;

    while (listIterator.hasNext()) {
      listIterator.next();
      listIterator.remove();
      assertFalse(list.contains(strings.get(index)));
      index++;
    }

  }

}