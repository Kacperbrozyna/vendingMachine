package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//unit tests for DAO
class ItemDAOImplTest {

    //creaing objects for tests
    ItemDAOImpl items = new ItemDAOImpl();
    ArrayList<vendingMachineItems> arrayItems = new ArrayList<>();
    vendingMachineItems itemOne = new vendingMachineItems("Pepsi", new BigDecimal("2"), 1);
    vendingMachineItems itemTwo = new vendingMachineItems("Snickers", new BigDecimal("1.35"), 2);

    //insert item test
    @org.junit.jupiter.api.Test
    void insertItem() {

        items.insertItem(itemOne);
        assertEquals(itemOne, items.getItem(0));
    }

    //insert items test
    @org.junit.jupiter.api.Test
    void insertItems() {

        ArrayList<vendingMachineItems> testItems = new ArrayList<>();
        testItems.add(itemOne);
        testItems.add(itemTwo);
        items.insertItems(testItems);
        assertEquals(testItems, items.getAllItems());
    }

    //update item test
    @org.junit.jupiter.api.Test
    void updateItem() {

        items.insertItem(itemOne);

        assertTrue(items.updateItem(itemTwo, 0));
        assertEquals(itemTwo, items.getItem(0));
    }


    //delete item test
    @org.junit.jupiter.api.Test
    void deleteItem() {

        items.insertItem(itemOne);

        assertEquals(1, items.getAllItems().size());
        assertTrue(items.deleteItem(0));
    }
}