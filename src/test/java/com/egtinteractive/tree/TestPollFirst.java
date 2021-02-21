package com.egtinteractive.tree;

import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.egtinteractive.TestUtilities.loadTreeWithData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TestPollFirst {

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
    public void testPollFirst(CustomTree<String> tree){

        loadTreeWithData(tree);

        Iterator<String> itr = tree.iterator();

        String firstElement = itr.next();

        assertEquals(firstElement, tree.pollFirst());

    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testPollFirstRootNull(CustomTree<String> tree){

        assertNull(tree.pollFirst());

    }

}
