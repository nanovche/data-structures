package com.egtinteractive.tree;

import static com.egtinteractive.TestUtilities.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRemove {

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
  public void testRemoveRootNull(CustomTree<String> tree){

    String string = UUID.randomUUID().toString();

    assertFalse(tree.remove(string));

  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
  public void testRemoveNoSuchElement(CustomTree<String> tree){

    loadTreeWithData(tree);

    String string;

    do{
      string = UUID.randomUUID().toString();
    }while(tree.contains(string));

    assertFalse(tree.remove(string));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveLessThanParentBothChildrenArePresent(CustomTree<Integer> tree){

    final int root = 20;
    final int removed = 10;
    final int left = 5;
    final int right = 15;

    tree.add(root);
    tree.add(removed);
    tree.add(left);
    tree.add(right);

    assertTrue(tree.remove(removed));
    assertFalse(tree.contains(removed));


  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveLessThanParentLeftChildIsPresent(CustomTree<Integer> tree){

    final int root = 20;
    final int removed = 10;
    final int left = 5;

    tree.add(root);
    tree.add(removed);
    tree.add(left);

    assertTrue(tree.remove(removed));
    assertFalse(tree.contains(removed));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveLessThanParentRightChildIsPresent(CustomTree<Integer> tree){

    final int root = 20;
    final int removed = 10;
    final int right = 15;

    tree.add(root);
    tree.add(removed);
    tree.add(right);

    assertTrue(tree.remove(removed));
    assertFalse(tree.contains(removed));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveLessThanParentBothChildrenAreNull(CustomTree<Integer> tree){

    final int root = 20;
    final int removed = 10;

    tree.add(root);
    tree.add(removed);

    assertTrue(tree.remove(removed));
    assertFalse(tree.contains(removed));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveMoreThanParentBothChildrenArePresent(CustomTree<Integer> tree){

    final int root = 20;
    final int removed = 30;
    final int left = 25;
    final int right = 35;

    tree.add(root);
    tree.add(removed);
    tree.add(left);
    tree.add(right);

    assertTrue(tree.remove(removed));
    assertFalse(tree.contains(removed));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveMoreThanParentLeftChildIsPresent(CustomTree<Integer> tree){

    final int root = 20;
    final int removed = 30;
    final int left = 25;

    tree.add(root);
    tree.add(removed);
    tree.add(left);

    assertTrue(tree.remove(removed));
    assertFalse(tree.contains(removed));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveMoreThanParentRightChildIsPresent(CustomTree<Integer> tree){

    final int root = 20;
    final int removed = 30;
    final int right = 35;

    tree.add(root);
    tree.add(removed);
    tree.add(right);

    assertTrue(tree.remove(removed));
    assertFalse(tree.remove(removed));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveMoreThanParentBothChildrenAreNull(CustomTree<Integer> tree){

    final int root = 20;
    final int removed = 30;

    tree.add(root);
    tree.add(removed);

    assertTrue(tree.remove(removed));
    assertFalse(tree.contains(removed));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveOneElement(CustomTree<Integer> tree){

    final int root = 20;

    tree.add(root);

    assertTrue(tree.remove(root));
    assertFalse(tree.contains(root));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveRoot(CustomTree<Integer> tree){

    final int root = 20;
    final int left = 15;

    tree.add(root);
    tree.add(left);

    assertTrue(tree.remove(root));
    assertFalse(tree.contains(root));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveRoot2(CustomTree<Integer> tree){

    final int root = 30;

    tree.add(root);
    tree.add(10);
    tree.add(20);
    tree.add(15);
    tree.add(25);

    assertTrue(tree.remove(root));
    assertFalse(tree.contains(root));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveRootLeftSubTreeNull(CustomTree<Integer> tree){

    final int root = 30;

    tree.add(root);
    tree.add(35);
    tree.add(40);
    tree.add(45);

    assertTrue(tree.remove(root));
    assertFalse(tree.contains(root));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveRootRightSubTreeNull(CustomTree<Integer> tree){

    final int root = 30;

    tree.add(root);
    tree.add(25);
    tree.add(20);
    tree.add(15);

    assertTrue(tree.remove(root));
    assertFalse(tree.contains(root));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class, invocationCount = 100)
  public void testRemoveRandom(CustomTree<Integer> tree){

    loadTreeWithDataInts(tree);

    int removed = new Random().nextInt();

    tree.add(removed);

    assertTrue(tree.remove(removed));
    assertFalse(tree.contains(removed));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveSpecific(CustomTree<Integer> tree){

    loadTreeWithDataHardCodedSet1(tree);

    assertTrue(tree.remove(23));
    assertFalse(tree.contains(23));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveSpecific2(CustomTree<Integer> tree){

    loadTreeWithDataHardCodedSet1(tree);

    assertTrue(tree.remove(34));
    assertFalse(tree.contains(34));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveSpecific3(CustomTree<Integer> tree){

    loadTreeWithDataHardCodedSet2(tree);

    assertTrue(tree.remove(50));
    assertFalse(tree.contains(50));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveSpecific4(CustomTree<Integer> tree){

    loadTreeWithDataHardCodedSet2(tree);

    assertTrue(tree.remove(11));
    assertFalse(tree.contains(11));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveSpecific5(CustomTree<Integer> tree){

    loadTreeWithDataHardCodedSet2(tree);

    assertTrue(tree.remove(50));
    assertFalse(tree.contains(50));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveSpecific6(CustomTree<Integer> tree){

    loadTreeWithDataHardCodedSet2(tree);

    assertTrue(tree.remove(12));
    assertFalse(tree.contains(12));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveSpecific7(CustomTree<Integer> tree){

    loadTreeWithDataHardCodedSet3(tree);

    assertTrue(tree.remove(50));
    assertFalse(tree.contains(50));

  }

  @Test(dataProvider = "emptyTreeInts", dataProviderClass = DataProviders.class)
  public void testRemoveSpecific8(CustomTree<Integer> tree){

    loadTreeWithDataHardCodedSet3(tree);

    List<Integer> ints = new ArrayList<>();

    ints.add(34);
    ints.add(50);
    ints.add(12);
    ints.add(11);
    ints.add(42);
    ints.add(41);
    ints.add(43);
    ints.add(65);
    ints.add(1);
    ints.add(16);
    ints.add(24);
    ints.add(26);
    ints.add(44);
    ints.add(2);
    ints.add(30);
    ints.add(66);
    ints.add(73);
    ints.add(49);
    ints.add(51);
    ints.add(57);
    ints.add(52);
    ints.add(18);
    ints.add(39);
    ints.add(37);

    for (int i = 0; i < tree.size(); i++) {
      assertTrue(tree.remove(ints.get(i)));
      assertFalse(tree.contains(ints.get(i)));
    }

  }

}
