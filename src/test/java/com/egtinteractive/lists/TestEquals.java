package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.FIVE_CAPACITY;
import static com.egtinteractive.TestUtilities.MAX_CAPACITY;
import static com.egtinteractive.TestUtilities.ONE_CAPACITY;
import static com.egtinteractive.TestUtilities.TEN_CAPACITY;
import static com.egtinteractive.TestUtilities.ZERO_CAPACITY;
import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;
import static com.egtinteractive.TestUtilities.loadListWithData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestEquals {

  @DataProvider(name = "dataProviderEqualsSameReference")
  public Object[][] dataProviderForEqualsSameReference() {

    final CustomList<String> myArrayList = new CustomArrayList<>(ZERO_CAPACITY);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList}, {myLinkedList}};

  }

  @Test(dataProvider = "dataProviderEqualsSameReference", invocationCount = 10)
  public void testEqualsSameReference(final CustomList<String> list) {

    assertEquals(list, list);

  }

  @DataProvider(name = "dataProviderEqualsOtherType")
  public Object[][] dataProviderForEqualsOtherType() {

    final CustomList<String> myArrayList = new CustomArrayList<>();
    final CustomList<String> myLinkedList = new CustomLinkedList<>();
    final Map<String, Integer> map = new HashMap<>();

    return new Object[][]{{myArrayList, map}, {myLinkedList, map}};

  }

  @Test(dataProvider = "dataProviderEqualsOtherType", invocationCount = 10)
  public void testEqualsOtherType(final CustomList<String> list, final Map<String, String> map) {

    assertNotEquals(map, list);

  }

  @DataProvider(name = "dataProviderEqualsDifferentSizes")
  public Object[][] dataProviderForEqualsDifferentSizes() {

    final CustomList<String> myArrayListSmaller = new CustomArrayList<>(MAX_CAPACITY - 5);
    final CustomList<String> myArrayListBigger = new CustomArrayList<>(MAX_CAPACITY);
    final CustomList<String> myLinkedListSmaller = new CustomLinkedList<>();
    final CustomList<String> myLinkedListBigger = new CustomLinkedList<>();

    loadListWithData(myArrayListSmaller, MAX_CAPACITY - 5);
    loadListWithData(myArrayListBigger, MAX_CAPACITY);
    loadListWithData(myLinkedListSmaller, MAX_CAPACITY - 5);
    loadListWithData(myLinkedListBigger, MAX_CAPACITY);

    return new Object[][]{{myArrayListSmaller, myArrayListBigger},
        {myLinkedListSmaller, myLinkedListBigger}};

  }

  @Test(dataProvider = "dataProviderEqualsDifferentSizes", invocationCount = 10)
  public void testEqualsDifferentSizes(final CustomList<String> list,
      final CustomList<String> otherList) {

    assertNotEquals(otherList, list);

  }

  @DataProvider(name = "dataProviderEqualsSameContent")
  public Object[][] dataProviderForEqualsSameContent() {

    final CustomList<String> myArrayList = new CustomArrayList<>();
    final CustomList<String> myOtherArrayList = new CustomArrayList<>();
    final CustomList<String> myLinkedList = new CustomLinkedList<>();
    final CustomList<String> myOtherLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, myOtherArrayList}, {myLinkedList, myOtherLinkedList}};

  }

  @Test(dataProvider = "dataProviderEqualsSameContent", invocationCount = 10)
  public void testEqualsSameContent(final CustomList<String> list,
      final CustomList<String> otherList) {

    for (int i = 0; i < 15; i++) {
      String string = UUID.randomUUID().toString();
      list.add(string);
      otherList.add(string);
    }

    assertEquals(otherList, list);
    assertEquals(list, otherList);

  }

  @DataProvider(name = "dataProviderEqualsSameContentDifferentCapacity")
  public Object[][] dataProviderForEqualsSameContentDifferentCapacity() {

    final CustomList<String> myArrayList = new CustomArrayList<>(TEN_CAPACITY);
    final CustomList<String> myOtherArrayList = new CustomArrayList<>(FIVE_CAPACITY);

    return new Object[][]{{myArrayList, myOtherArrayList}};

  }

  @Test(dataProvider = "dataProviderEqualsSameContentDifferentCapacity")
  public void testEqualsSameContentDifferentCapacity(final CustomList<String> list,
      final CustomList<String> otherList) {

    for (int i = 0; i < 5; i++) {
      String string = UUID.randomUUID().toString();
      list.add(string);
      otherList.add(string);
    }

    boolean result = list.equals(otherList);

    assertTrue(result);

  }

  @DataProvider(name = "dataProviderEqualsDifferentContent")
  public Object[][] dataProviderForEqualsDifferentContent() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myOtherArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();
    final CustomList<String> myOtherLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, myOtherArrayList, capacity},
        {myLinkedList, myOtherLinkedList, capacity}};

  }

  @Test(dataProvider = "dataProviderEqualsDifferentContent", invocationCount = 10)
  public void testEqualsDifferentContent(final CustomList<String> list,
      final CustomList<String> otherList,
      final int capacity) {

    loadListWithData(list, capacity);
    loadListWithData(otherList, capacity);

    assertNotEquals(list, otherList);

  }

  @DataProvider(name = "dataProviderEqualsNullProperty")
  public Object[][] dataProviderForEqualsNullProperty() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, capacity}, {myLinkedList, capacity}};

  }

  @Test(dataProvider = "dataProviderEqualsNullProperty", invocationCount = 10)
  public void testEqualsForNullProperty(final CustomList<String> list, final int capacity) {

    loadListWithData(list, capacity);

    assertNotEquals(list, null);

  }

  @DataProvider(name = "dataProviderEqualsTransitiveProperty")
  public Object[][] dataProviderForEqualsTransitiveProperty() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    final CustomList<String> firstArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> secondArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> thirdArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> firstLinkedList = new CustomLinkedList<>();
    final CustomList<String> secondLinkedList = new CustomLinkedList<>();
    final CustomList<String> thirdLinkedList = new CustomLinkedList<>();

    return new Object[][]{{firstArrayList, secondArrayList, thirdArrayList, capacity},
        {firstLinkedList, secondLinkedList, thirdLinkedList, capacity}};

  }

  @Test(dataProvider = "dataProviderEqualsTransitiveProperty", invocationCount = 10)
  public void testEqualsForTransitiveProperty(final CustomList<String> firstList,
      final CustomList<String> secondList, final CustomList<String> thirdList, final int capacity) {

    for (int i = 0; i < capacity; i++) {
      String string = UUID.randomUUID().toString();
      firstList.add(string);
      secondList.add(string);
      thirdList.add(string);
    }

    assertEquals(secondList, firstList);
    assertEquals(thirdList, secondList);
    assertEquals(thirdList, firstList);

  }

}