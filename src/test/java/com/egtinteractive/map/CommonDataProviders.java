package com.egtinteractive.map;

import static com.egtinteractive.TestUtilities.MAX_CAPACITY;
import static com.egtinteractive.TestUtilities.ONE_CAPACITY;
import static com.egtinteractive.TestUtilities.ZERO_CAPACITY;
import static com.egtinteractive.TestUtilities.generateRandomNumberWithinRange;

import org.testng.annotations.DataProvider;

public class CommonDataProviders {

  @DataProvider(name = "provider")
  public Object[][] dataProvider() {

    final int capacity = generateRandomNumberWithinRange(ZERO_CAPACITY, MAX_CAPACITY);
    CustomMap<String, Integer> map = new CustomHashMap<>(capacity);
    return new Object[][]{{map, capacity}};

  }

  @DataProvider(name = "providerZeroCapacity")
  public Object[][] dataProviderZeroCapacity() {

    final int capacity = ZERO_CAPACITY;
    CustomMap<String, Integer> map = new CustomHashMap<>(capacity);
    return new Object[][]{{map, capacity}};

  }

  @DataProvider(name = "providerNotZeroCapacity")
  public Object[][] dataProviderNotZeroCapacity() {

    final int capacity = generateRandomNumberWithinRange(ONE_CAPACITY, MAX_CAPACITY);
    CustomMap<String, Integer> map = new CustomHashMap<>(capacity);
    return new Object[][]{{map, capacity}};

  }

}

