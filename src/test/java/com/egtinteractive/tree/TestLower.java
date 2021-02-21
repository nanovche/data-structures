package com.egtinteractive.tree;

import static com.egtinteractive.TestUtilities.loadTreeWithData;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.UUID;
import org.testng.annotations.Test;

public class TestLower {

  @org.testng.annotations.Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
  public void testLowerRootNull(CustomTree<String> tree){

    String string = UUID.randomUUID().toString();

    assertNull(tree.lower(string));

  }

  @org.testng.annotations.Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
  public void testLower(CustomTree<String> tree){

    loadTreeWithData(tree);

    String lowest = tree.pollFirst();
    tree.add(lowest);

    String string;

    do {
      string = UUID.randomUUID().toString();
    }while (lowest.compareTo(string) >= 0);

    assertTrue(tree.lower(string).compareTo(string) < 0);

  }

  @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
  public void testLowerNoSuchElement(CustomTree<String> tree){

    loadTreeWithData(tree);

    String lowest = tree.pollFirst();

    String string;

    do {
      string = UUID.randomUUID().toString();
    }while (lowest.compareTo(string) < 0);

    assertNull(tree.lower(lowest));

  }


}
