package com.egtinteractive.tree;

import java.util.Iterator;
import org.testng.annotations.Test;

import static com.egtinteractive.TestUtilities.loadTreeWithData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TestPollLast {

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 10)
    public void testPollLast(CustomTree<String> tree){

        loadTreeWithData(tree);

        Iterator<String> itr = tree.iterator();

        String lastElement = null;

        while(itr.hasNext()){
            lastElement = itr.next();
        }

        assertEquals(tree.pollLast(), lastElement);
    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testPollLastNull(CustomTree<String> tree){

        assertNull(tree.pollLast());

    }

}
