package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class vendingMachineController {

    //defining objects and variables
    final int LENGTH_OF_COINS = coinTypes.values().length;

    Logger log = Logger.getLogger(vendingMachineController.class.getName());
    ConsoleHandler handler = new ConsoleHandler();

    ItemDAOImpl unavailableItems = new ItemDAOImpl();
    ItemDAOImpl availableItems = new ItemDAOImpl();
    Coins customerCoins = new Coins();
    fileManager managerFile = new fileManager();

    private int[] amountOfCoins = new int[LENGTH_OF_COINS];
    private BigDecimal totalAmountMoney = new BigDecimal("0");

    //constructor
    public vendingMachineController() {
        log.setLevel(Level.INFO);
        handler.setFormatter(new SimpleFormatter());
        log.addHandler(handler);
    }

    //setter
    public void setAmountOfCoins(int[] amountOfCoins) {
        this.amountOfCoins = amountOfCoins;
    }

    //getter
    public BigDecimal getTotalAmountMoney() {
        return totalAmountMoney;
    }

    //method to set total amount of money
    public void addCustomerCoins() {
        totalAmountMoney = customerCoins.addUpCoins(amountOfCoins, totalAmountMoney);
    }

    //getter
    public ArrayList<vendingMachineItems> getAvailableItems() {
        return availableItems.getAllItems();
    }

    //function to read from the file
    public void readFromFile() {

        //logger outputting to console, log event
        log.info("Method call to read CSV");

        //method to read from file
        managerFile.readFile();

        //inserting items
        availableItems.insertItems(managerFile.getAvailableVendingMachineItems());
        unavailableItems.insertItems(managerFile.getUnavailableVendingMachineItems());
    }

    public void writeToFile() {

        //logger outputting to console, log event
        log.info("Method call to write CSV");

        //setting items
        managerFile.setAvailableVendingMachineItems(availableItems.getAllItems());
        managerFile.setUnavailableVendingMachineItems(unavailableItems.getAllItems());

        //method to write to file
        managerFile.writeFile();
    }

    public boolean vendItem(int index) {

        //try catch block
        try {

            // if there is enough money vend the item and subtract money
            if (totalAmountMoney.compareTo(availableItems.getItem(index).price) == 0 || totalAmountMoney.compareTo(availableItems.getItem(index).price) == 1) {
                availableItems.getItem(index).setNumbOfItems(availableItems.getItem(index).numbOfItems - 1);
                totalAmountMoney = totalAmountMoney.subtract(availableItems.getItem(index).price);

                //if the number of items is 0 or less, add it to unavailable items and remove it from available items
                if (availableItems.getItem(index).numbOfItems <= 0) {
                    unavailableItems.insertItem(availableItems.getItem(index));
                    availableItems.deleteItem(index);
                }

                //return true if item vended
                return true;
            } else {

                //throw custom exception
                throw new InsufficientFundsException();
            }
        } catch (InsufficientFundsException e) {

            //calling custom exception function
            e.HowToRecover();
        }
        return false;
    }

    public int[] getChange() {

        //changing amount of money into coins
        amountOfCoins = customerCoins.divideIntoCoins(totalAmountMoney);

        //setting amount of money
        totalAmountMoney = new BigDecimal("0");

        //returning the array of coins
        return amountOfCoins;
    }
}
