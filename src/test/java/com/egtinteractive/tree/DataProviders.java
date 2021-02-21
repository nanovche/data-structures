package com.egtinteractive.tree;

import com.egtinteractive.TestUtilities;
import org.testng.annotations.DataProvider;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class DataProviders {

    @DataProvider(name = "emptyTree")
    public Object[][] emptyTreeProvider(){

        CustomTree<String> tree = new CustomBinaryTree<>();

        return new Object[][]{{tree}};
    }

    @DataProvider(name = "emptyTreeInts")
    public Object[][] emptyTreeProviderInts(){

        CustomTree<Integer> tree = new CustomBinaryTree<>();

        return new Object[][]{{tree}};
    }

    @DataProvider(name = "twoEmptyTrees")
    public Object[][] twoEmptyTreesProvider(){

        CustomTree<String> treeX = new CustomBinaryTree<>();
        CustomTree<String> treeY = new CustomBinaryTree<>();

        return new Object[][]{{treeX, treeY}};
    }

    @DataProvider(name = "treeWithMaxCapacity")
    public Object[][] TreeProviderWithMaxCapacity(){

        CustomTree<String> tree = new CustomBinaryTree<>();

        TestUtilities.loadTreeWithData(tree);

        return new Object[][]{{tree, TestUtilities.MAX_CAPACITY}};
    }

    @DataProvider(name = "providerTwoEqualTrees")
    public Object[][] providerConsistent(){

        CustomTree<String> treeX = new CustomBinaryTree<>();
        CustomTree<String> treeY = new CustomBinaryTree<>();

        for (int i = 0; i < TestUtilities.MAX_CAPACITY; i++) {
            String string = UUID.randomUUID().toString();
            treeX.add(string);
            treeY.add(string);
        }

        return new Object[][]{{treeX, treeY}};
    }

    @DataProvider(name = "providerThreeEqualTrees")
    public Object[][] providerTransitive(){

        CustomTree<String> treeX = new CustomBinaryTree<>();
        CustomTree<String> treeY = new CustomBinaryTree<>();
        CustomTree<String> treeZ = new CustomBinaryTree<>();

        for (int i = 0; i < TestUtilities.MAX_CAPACITY; i++) {
            String string = UUID.randomUUID().toString();
            treeX.add(string);
            treeY.add(string);
            treeZ.add(string);
        }

        return new Object[][]{{treeX, treeY, treeZ}};
    }

    @DataProvider(name = "providerOtherInstance")
    public Object[][] providerOtherInstance(){

        CustomTree<String> treeX = new CustomBinaryTree<>();
        TreeSet<String> treeY = new TreeSet<>();

        for (int i = 0; i < TestUtilities.MAX_CAPACITY; i++) {
            String string = UUID.randomUUID().toString();
            treeX.add(string);
            treeY.add(string);
        }

        return new Object[][]{{treeX, treeY}};
    }

    @DataProvider(name = "providerDifferentSizes")
    public Object[][] providerDifferentSizes(){

        CustomTree<String> treeX = new CustomBinaryTree<>();
        CustomTree<String> treeY = new CustomBinaryTree<>();

        for (int i = 0; i < TestUtilities.MAX_CAPACITY; i++) {
            String string = UUID.randomUUID().toString();
            treeX.add(string);
            treeY.add(string);
        }

        return new Object[][]{{treeX, treeY}};
    }

}
