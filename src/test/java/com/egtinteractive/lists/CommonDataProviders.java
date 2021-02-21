package com.egtinteractive.lists;

import static com.egtinteractive.TestUtilities.MAX_CAPACITY;
import static com.egtinteractive.TestUtilities.ONE_CAPACITY;
import static com.egtinteractive.TestUtilities.TEN_CAPACITY;
import static com.egtinteractive.TestUtilities.TWO_CAPACITY;
import static com.egtinteractive.TestUtilities.ZER0_INDEX;
import static com.egtinteractive.TestUtilities.ZERO_CAPACITY;
import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;

import org.testng.annotations.DataProvider;

public class CommonDataProviders {

  @DataProvider(name = "dataProviderCapacity")
  public Object[][] dataProviderZeroCapacity() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, capacity}, {myLinkedList, capacity}};

  }

  @DataProvider(name = "dataProviderOneMinCapacity")
  public Object[][] dataProviderOneCapacity() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, capacity}, {myLinkedList, capacity}};

  }

  @DataProvider(name = "dataProviderTwoMinCapacity")
  public Object[][] dataProviderTwoCapacity() {

    final int capacity = generateRandomNumberWithinRange(TWO_CAPACITY, MAX_CAPACITY);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, capacity}, {myLinkedList, capacity}};

  }

  @DataProvider(name = "dataProviderCapacityPlusIndex")
  public Object[][] dataProviderForInsert() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    final int index = generateRandomNumberWithinRange(ZER0_INDEX, capacity - 1);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, capacity, index}, {myLinkedList, capacity, index}};

  }

  @DataProvider(name = "dataProviderCapacityOnePlusIndex")
  public Object[][] dataProviderForSet1() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);
    final int index = generateRandomNumberWithinRange(ZER0_INDEX, capacity - 1);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myLinkedList, capacity, 0}};

  }

  @DataProvider(name = "dataProviderCapacityTwoPlusIndex")
  public Object[][] dataProviderForSet2() {

    final int capacity = generateRandomNumberWithinRange(2, MAX_CAPACITY);
    final int index = generateRandomNumberWithinRange(ZER0_INDEX, capacity - 1);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, capacity, index}, {myLinkedList, capacity, index}};

  }

  @DataProvider(name = "dataProviderIndexOutOfBounds")
  public Object[][] dataProviderForIndexOutOfBounds() {
    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    final int indexLessThanZero = generateRandomNumberWithinRange(Integer.MIN_VALUE, -1);
    final int indexMoreThanCapacity = generateRandomNumberWithinRange(capacity, Integer.MAX_VALUE);
    final CustomList<String> myArrayList = new CustomArrayList<>(capacity);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, capacity, indexLessThanZero},
        {myArrayList, capacity, indexMoreThanCapacity},
        {myLinkedList, capacity, indexLessThanZero},
        {myLinkedList, capacity, indexMoreThanCapacity}};
  }

  @DataProvider(name = "dataProviderIndexOutOfBoundsZeroIndexZeroCapacity")
  public Object[][] dataProviderForIndexOutOfBoundsZeroIndexZeroCapacity() {

    final CustomList<String> myArrayList = new CustomArrayList<>(0);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList, ZERO_CAPACITY, ZER0_INDEX},
        {myLinkedList, ZERO_CAPACITY, ZER0_INDEX}};
  }

  @DataProvider(name = "dataProviderArrayList")
  public Object[][] dataProviderForClearArrayList() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    final CustomList<String> myArrayList = new CustomArrayList<>();

    return new Object[][]{{myArrayList, capacity}};

  }

  @DataProvider(name = "dataProviderLinkedList")
  public Object[][] dataProviderForContainsLinkedListHeadNull() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myLinkedList, capacity}};
  }

  @DataProvider(name = "dataProviderLinkedListZeroIndex")
  public Object[][] dataProviderForInsertAtZeroLinkedList() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);
    final int index = 0;
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myLinkedList, capacity, index}};

  }

  @DataProvider(name = "dataProviderForGet")
  public Object[][] dataProviderForGet() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);
    final int index = generateRandomNumberWithinRange(ZER0_INDEX, capacity - 1);
    final CustomList<String> myArrayList = new CustomArrayList<>();
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myLinkedList, capacity, index}, {myArrayList, capacity, index}};

  }

  @DataProvider(name = "dataProviderForGetArrayList")
  public Object[][] dataProviderForGetArrayList() {

    final CustomList<String> myArrayList = new CustomArrayList<>();

    return new Object[][]{{myArrayList, TEN_CAPACITY}};

  }

  @DataProvider(name = "dataProviderIterator")
  public Object[][] dataProviderForIterator() {

    final CustomList<String> myArrayList = new CustomArrayList<>();
    final CustomList<String> myLinkedList = new CustomLinkedList<>();

    return new Object[][]{{myArrayList}, {myLinkedList}};

  }

}
