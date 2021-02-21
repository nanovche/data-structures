package com.egtinteractive.tree;

import com.egtinteractive.TestUtilities;
import org.testng.annotations.Test;

import java.util.TreeSet;
import java.util.UUID;

import static com.egtinteractive.TestUtilities.loadTreeWithData;
import static org.testng.Assert.*;

public class TestEquals {

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testReflexive(CustomTree<String> tree){

        boolean r = tree.equals(tree);

        assertTrue(r);

    }

    @Test(dataProvider = "providerThreeEqualTrees", dataProviderClass = DataProviders.class)
    public void testTransitive(CustomTree<String> treeX, CustomTree<String> treeY, CustomTree<String> treeZ){

        boolean result = treeX.equals(treeY);

        assertTrue(result);

        result = treeY.equals(treeZ);

        assertTrue(result);

        result = treeX.equals(treeZ);

        assertTrue(result);

    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testNonNullity(CustomTree<String> tree){

        boolean result = tree.equals(null);

        assertFalse(result);

    }

    @Test(dataProvider = "providerTwoEqualTrees", dataProviderClass = DataProviders.class)
    public void testConsistent(CustomTree<String> treeX, CustomTree<String> treeY){

        boolean result = treeX.equals(treeY);

        assertTrue(result);

        result = treeX.equals(treeY);

        assertTrue(result);

        result = treeX.equals(treeY);

        assertTrue(result);

    }

    @Test(dataProvider = "providerTwoEqualTrees", dataProviderClass = DataProviders.class)
    public void testSymmetrical(CustomTree<String> treeX, CustomTree<String> treeY){

        boolean result = treeX.equals(treeY);

        assertTrue(result);

        result = treeY.equals(treeX);

        assertTrue(result);

    }

    @Test(dataProvider = "providerOtherInstance", dataProviderClass = DataProviders.class)
    public void testOtherInstance(CustomTree<String> treeX, TreeSet<String> treeY){

        boolean result = treeX.equals(treeY);

        assertFalse(result);

    }

    @Test(dataProvider = "providerTwoEqualTrees", dataProviderClass = DataProviders.class)
    public void testDifferentSizes(CustomTree<String> treeX, CustomTree<String> treeY){

        String string = UUID.randomUUID().toString();

        treeX.add(string);

        boolean result = treeX.equals(treeY);

        assertFalse(result);

    }

    @Test(dataProvider = "twoEmptyTrees", dataProviderClass = DataProviders.class)
    public void testSizeZero(CustomTree<String> treeX, CustomTree<String> treeY){

        boolean result = treeX.equals(treeY);

        assertTrue(result);

    }

    @Test(dataProvider = "twoEmptyTrees", dataProviderClass = DataProviders.class)
    public void testDifferentData(CustomTree<String> treeX, CustomTree<String> treeY){

        loadTreeWithData(treeX);
        loadTreeWithData(treeY);

        boolean result = treeX.equals(treeY);

        assertFalse(result);

    }

}
