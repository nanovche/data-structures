package com.egtinteractive.tree;

import com.egtinteractive.TestUtilities;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.assertTrue;

public class TestAdd {

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testAdd(CustomTree<String> tree){

        for (int i = 0; i < TestUtilities.MAX_CAPACITY; i++) {
            String string = UUID.randomUUID().toString();
            tree.add(string);
            assertTrue(tree.contains(string));
        }

    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class,
    expectedExceptions = IllegalArgumentException.class)
    public void testAddNull(CustomTree<String> tree){

        TestUtilities.loadTreeWithData(tree);

        tree.add(null);

    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class,
    expectedExceptions = IllegalArgumentException.class)
    public void testAddDuplicate(CustomTree<String> tree){

        TestUtilities.loadTreeWithData(tree);

        String string = UUID.randomUUID().toString();

        tree.add(string);
        tree.add(string);

    }

}
