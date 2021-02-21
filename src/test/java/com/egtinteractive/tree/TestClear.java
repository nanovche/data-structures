package com.egtinteractive.tree;

import org.testng.annotations.Test;

import static com.egtinteractive.TestUtilities.loadTreeWithData;
import static org.testng.Assert.assertEquals;

public class TestClear {

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testClearMethod(CustomTree<String> tree){

        loadTreeWithData(tree);

        tree.clear();

        assertEquals(tree.size(), 0);

    }

}
