package com.egtinteractive.tree;

import com.egtinteractive.TestUtilities;
import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.egtinteractive.TestUtilities.loadTreeWithData;
import static org.testng.Assert.*;

public class TestHigher {

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testHigherRootNull(CustomTree<String> tree){

        String string = UUID.randomUUID().toString();

        assertNull(tree.higher(string));

    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class, invocationCount = 1)
    public void testHigher(CustomTree<String> tree){

        loadTreeWithData(tree);

        String biggest = tree.pollLast();
        tree.add(biggest);

        String string;

        do {
            string = UUID.randomUUID().toString();
        }while (biggest.compareTo(string) <= 0);

        String returned = tree.higher(string);
        assertTrue(returned.compareTo(string) > 0);

    }

    @Test(dataProvider = "emptyTree", dataProviderClass = DataProviders.class)
    public void testHigherNoSuchElement(CustomTree<String> tree){

        loadTreeWithData(tree);

        String biggest = tree.pollLast();

        String string;

        do {
            string = UUID.randomUUID().toString();
        }while (biggest.compareTo(string) > 0);

        assertNull(tree.higher(biggest));

    }

}
