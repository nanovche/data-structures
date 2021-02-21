package com.egtinteractive.tree;

import org.testng.annotations.Test;

import java.util.UUID;

import static com.egtinteractive.TestUtilities.loadTreeWithData;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestContains {

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testContainsFalseRootNull(CustomTree<String> tree){

        String string = UUID.randomUUID().toString();

        assertFalse(tree.contains(string));

    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testContainsFalseElementNotPresent(CustomTree<String> tree){

        loadTreeWithData(tree);

        String string = UUID.randomUUID().toString();

        assertFalse(tree.contains(string));

    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
    public void testContainsTrue(CustomTree<String> tree){

        loadTreeWithData(tree);

        String string = UUID.randomUUID().toString();

        tree.add(string);

        assertTrue(tree.contains(string));

    }

}
