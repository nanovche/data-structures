package com.egtinteractive.tree;

import com.egtinteractive.TestUtilities;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestHashCode {

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testSameValue(CustomTree<String> tree){

        TestUtilities.loadTreeWithData(tree);

        final int hashCode = tree.hashCode();

        assertEquals(tree.hashCode(), hashCode);
        assertEquals(tree.hashCode(), hashCode);
        assertEquals(tree.hashCode(), hashCode);

    }

    @Test(dataProvider = "providerTwoEqualTrees", dataProviderClass = DataProviders.class)
    public void testEqualObjsEqualHashCodes(CustomTree<String> treeX, CustomTree<String> treeY){

        assertEquals(treeX, treeY);
        assertEquals(treeX.hashCode(), treeY.hashCode());

    }

}
