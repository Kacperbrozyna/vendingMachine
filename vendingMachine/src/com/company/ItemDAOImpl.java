package com.company;

import java.util.ArrayList;

//implementation of DAO

public class ItemDAOImpl implements ItemDAO {

    //creating collection of objects
    private ArrayList<vendingMachineItems> itemsDAO = new ArrayList<vendingMachineItems>();


    //overriding all the functions

    //method to insert item
    @Override
    public void insertItem(vendingMachineItems item) {
        itemsDAO.add(item);
    }

    //method to insert a collection of items into another collection
    @Override
    public void insertItems(ArrayList<vendingMachineItems> items) {
        itemsDAO.addAll(items);
    }

    //method to update an item
    @Override
    public boolean updateItem(vendingMachineItems item, int index) {
        if (itemsDAO.contains(itemsDAO.get(index))) {
            itemsDAO.set(index, item);
            return true;
        } else {
            return false;
        }
    }

    //method to get item from collection
    @Override
    public vendingMachineItems getItem(int index) {
        return itemsDAO.get(index);
    }

    //method to get collection
    @Override
    public ArrayList<vendingMachineItems> getAllItems() {
        return itemsDAO;
    }

    //method to delete item from collection
    @Override
    public boolean deleteItem(int id) {
        if (itemsDAO.contains(itemsDAO.get(id))) {
            itemsDAO.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
