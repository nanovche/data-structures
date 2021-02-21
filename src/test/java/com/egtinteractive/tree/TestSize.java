package com.egtinteractive.tree;

import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.assertEquals;

public class TestSize {

    @Test(dataProvider = "treeWithMaxCapacity", dataProviderClass = DataProviders.class)
    public void testContainsTrue(CustomTree<String> tree, int currentSize){

        assertEquals(tree.size(), currentSize);

        String string = UUID.randomUUID().toString();

        tree.add(string);

        assertEquals(tree.size(), currentSize + 1);

        tree.remove(string);

        assertEquals(tree.size(), currentSize);

    }

}
