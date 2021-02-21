package com.egtinteractive.tree;

import static com.egtinteractive.TestUtilities.loadTreeWithData;
import static com.egtinteractive.TestUtilities.loadTreeWithDataForIterator;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestIterator {

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
  public void testHasNextOnEmptyTree(final CustomTree<String> tree) {

    Iterator<String> treeIterator = tree.iterator();

    assertFalse(treeIterator.hasNext());

  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
  public void testHasNextOnOneItem(final CustomTree<String> tree) {

    String string = UUID.randomUUID().toString();

    tree.add(string);

    Iterator<String> treeIterator = tree.iterator();

    assertTrue(treeIterator.hasNext());

  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class,
  expectedExceptions = NoSuchElementException.class)
  public void testNextOnEmptyTree(final CustomTree<String> tree) {

    Iterator<String> treeIterator = tree.iterator();

    treeIterator.next();

  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
  public void testNextAndHasNextOnOneItem(final CustomTree<String> tree) {

    String string = UUID.randomUUID().toString();

    tree.add(string);

    Iterator<String> treeIterator = tree.iterator();

    assertTrue(treeIterator.hasNext());
    assertEquals(treeIterator.next(), string);
    assertFalse(treeIterator.hasNext());

  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
  public void testNextAndHasNextOnWholeTree(final CustomTree<String> tree) {

    List<String> list = new ArrayList<>();

    loadTreeWithDataForIterator(tree, list);

    Collections.sort(list);

    Iterator<String> treeIterator = tree.iterator();
    Iterator<String> listIterator = list.iterator();

    while(listIterator.hasNext()){
      assertTrue(treeIterator.hasNext());
      assertEquals(treeIterator.next(), listIterator.next());
    }

    assertFalse(treeIterator.hasNext());

  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class,
   expectedExceptions = IllegalStateException.class)
  public void testRemoveOnEmptyList(final CustomTree<String> tree) {

    Iterator<String> treeIterator = tree.iterator();

    treeIterator.remove();
  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class,
   expectedExceptions = IllegalStateException.class)
  public void testRemoveMoreThanOnceInARow(final CustomTree<String> tree) {

    String string = UUID.randomUUID().toString();

    tree.add(string);

    Iterator<String> treeIterator = tree.iterator();

    while(treeIterator.hasNext()){
      treeIterator.next();
      treeIterator.remove();
    }

    treeIterator.remove();
  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
  public void testRemove(final CustomTree<String> tree) {

    loadTreeWithData(tree);

    Iterator<String> treeIterator = tree.iterator();

    while(treeIterator.hasNext()){
      String string =treeIterator.next();
      treeIterator.remove();
      assertFalse(tree.contains(string));
    }

  }

}
