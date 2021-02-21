package com.egtinteractive;

import com.egtinteractive.lists.CustomList;
import com.egtinteractive.map.CustomMap;
import com.egtinteractive.tree.CustomTree;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TestUtilities {

  public static final int MAX_CAPACITY = 50;
  public static final int ZERO_CAPACITY = 0;
  public static final int ONE_CAPACITY = 1;

  public static final int TEN_CAPACITY = 10;
  public static final int FIVE_CAPACITY = 5;
  public static final int TWO_CAPACITY = 2;
  public static final int ZER0_INDEX = 0;
  public static final int FIVE_INDEX = 5;
  public static final int NINE_INDEX = 9;

  private TestUtilities() {
  }

  public static int generateRandomNumberWithinRange(int min, int max) {
    final int range = max - min + 1;
    return (int) (Math.random() * range) + min;
  }

  public static void loadListWithData(final CustomList<String> list, final int capacity) {

    for (int i = 0; i < capacity / 2; i++) {
      list.add(UUID.randomUUID().toString());
      list.add(null);
    }

    if (capacity % 2 != 0) {
      list.add( UUID.randomUUID().toString());
    }

  }

  public static void loadListWithDataNoNulls(final CustomList<String> list, final int capacity) {

    for (int i = 0; i < capacity / 2; i++) {
      list.add(UUID.randomUUID().toString());
    }

  }

  public static void fillMapWithoutNulls(final CustomMap<String, Integer> map, int capacity) {

    for (int i = 0; i <= capacity; i++) {
      String key = UUID.randomUUID().toString();
      int value = new Random().nextInt();
      map.put(key, value);
    }

  }

  public static void fillMapWithNullKey(final CustomMap<String, Integer> map, int capacity) {

    for (int i = 0; i < capacity; i++) {
      map.put(i == 0 ? null : UUID.randomUUID().toString(),
          new Random().nextInt());
    }

  }

  public static void fillMapWithNullKeyAndNullValues(final CustomMap<String, Integer> map,
      int capacity) {

    for (int i = 0; i < capacity; i++) {
      map.put(i == 0 ? null : UUID.randomUUID().toString(),
          i % 2 == 0 ? null : new Random().nextInt());
    }

  }

  public static void loadTreeWithData(CustomTree<String> tree) {

    System.out.println("========tree======");

    for (int i = 0; i < MAX_CAPACITY; i++) {
      String string = UUID.randomUUID().toString();
      tree.add(string);
      System.out.println(string);
    }

  }

  public static void loadTreeWithDataInts(CustomTree<Integer> tree) {

    for (int i = 0; i < MAX_CAPACITY; i++) {
      tree.add(new Random().nextInt());
    }

  }

  public static void loadTreeWithDataHardCodedSet1(CustomTree<Integer> tree) {

    tree.add(34);
    tree.add(23);
    tree.add(43);
    tree.add(12);
    tree.add(25);
    tree.add(42);
    tree.add(65);
    tree.add(1);
    tree.add(16);
    tree.add(24);
    tree.add(26);
    tree.add(44);
    tree.add(79);

  }

  public static void loadTreeWithDataForIterator(CustomTree<String> tree, List<String> list) {

    for (int i = 0; i < MAX_CAPACITY; i++) {
      String string = UUID.randomUUID().toString();
      tree.add(string);
      list.add(string);
    }

  }

  public static void loadTreeWithDataHardCodedSet2(CustomTree<Integer> tree) {

    tree.add(34);
    tree.add(50);
    tree.add(12);
    tree.add(11);
    tree.add(42);
    tree.add(41);
    tree.add(43);
    tree.add(65);
    tree.add(1);
    tree.add(16);
    tree.add(24);
    tree.add(26);
    tree.add(44);
    tree.add(2);
    tree.add(30);
    tree.add(66);
    tree.add(73);
    tree.add(49);
    tree.add(51);
    tree.add(57);
    tree.add(52);
    tree.add(18);
    tree.add(39);
    tree.add(37);
    tree.add(45);

  }

  public static void loadTreeWithDataHardCodedSet3(CustomTree<Integer> tree) {

    tree.add(34);
    tree.add(50);
    tree.add(12);
    tree.add(11);
    tree.add(42);
    tree.add(41);
    tree.add(43);
    tree.add(65);
    tree.add(1);
    tree.add(16);
    tree.add(24);
    tree.add(26);
    tree.add(44);
    tree.add(2);
    tree.add(30);
    tree.add(66);
    tree.add(73);
    tree.add(49);
    tree.add(51);
    tree.add(57);
    tree.add(52);
    tree.add(18);
    tree.add(39);
    tree.add(37);

  }

}
