package com.company;

import java.util.ArrayList;

//DAO interface
public interface ItemDAO {

    public void insertItem(vendingMachineItems item);

    public void insertItems(ArrayList<vendingMachineItems> items);

    public boolean updateItem(vendingMachineItems item, int index);

    public vendingMachineItems getItem(int index);

    public ArrayList<vendingMachineItems> getAllItems();

    public boolean deleteItem (int id);
}
