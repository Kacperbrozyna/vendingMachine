package com.company;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        //creating new objects
        Viewer outputViewer = new Viewer();
        vendingMachineController itemController = new vendingMachineController();

        //creating new variables
        int userChoice = 0, numbOfItems;
        boolean bAppRunning = true;

        //welcoming the user to the vending machine
        outputViewer.welcome();

        //read from file
        itemController.readFromFile();

        //while the vending machine is running
        while (bAppRunning) {

            //updating values
            userChoice = outputViewer.outputChoice(itemController.getAvailableItems());
            numbOfItems = itemController.getAvailableItems().size();

            //if the user choice is inbetween 1 and numb of items
            if (userChoice <= numbOfItems) {

                //vending the item and returning change
                if (itemController.vendItem(userChoice - 1)) {
                    outputViewer.outputChange(itemController.getChange());
                }
                else
                {
                    outputViewer.outputMoney(itemController.getTotalAmountMoney());
                }
            }

            //dynamic if statement to check for other options given to user
            else if (userChoice == (numbOfItems + 1)) {

                //function to output balance
                outputViewer.outputMoney(itemController.getTotalAmountMoney());

            } else if (userChoice == (numbOfItems + 2)) {

                //setting array of coins and calling function to add them up
                itemController.setAmountOfCoins(outputViewer.inputMoney());
                itemController.addCustomerCoins();

            } else if (userChoice == (numbOfItems + 3)) {

                //function to return change
                outputViewer.outputChange(itemController.getChange());

            } else if (userChoice == (numbOfItems + 4)) {

                //exit the application and return change if there is money in machine
                bAppRunning = false;
                if (itemController.getTotalAmountMoney().compareTo(new BigDecimal("0")) == 1) {
                    outputViewer.outputChange(itemController.getChange());
                }
            }
        }

        //writing to file
        itemController.writeToFile();

        //message to user
        outputViewer.goodbye();
    }
}
