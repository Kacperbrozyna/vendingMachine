package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;


public class Viewer {

    private coinTypes typeOfCoins;

    //outputting message to the user
    public void welcome() {
        System.out.println("Hello, Welcome to the Better Best Vending Machine");
    }

    //getting int input from the user
    public int inputInt(int lowerLimit, int upperLimit) {

        //creating variables
        Scanner intInput = new Scanner(System.in);
        int userInput = -1;
        String inputHandling;

        //do while try catch block for getting int input
        do {
            try {
                userInput = intInput.nextInt();
            } catch (Exception e) {

                //outputting message and getting rid of /n from input
                System.out.println("That is not a valid amount, please try again \n");
                inputHandling = intInput.nextLine();
            }
        } while (userInput < lowerLimit || userInput > upperLimit);

        //returning int from input
        return userInput;
    }

    public int[] inputMoney() {

        //creating new array
        int[] coins = new int[7];

        //for the length of enum
        //get input from user for each coin
        for (int i = 0; i < coins.length; i++) {
            typeOfCoins = coinTypes.values()[i];
            System.out.println("How many " + typeOfCoins.toString() + " coins would you like to deposit");
            coins[i] = inputInt(0, 100);
        }

        //return the filled array of coins
        return coins;
    }

    public int outputChoice(ArrayList<vendingMachineItems> items) {
        System.out.println("Here are your choices \n");

        //new variable
        int currentLength = 0;

        //for each item in item collection
        for (vendingMachineItems item : items) {

            //incrementing variable
            ++currentLength;

            //outputting item choice
            System.out.println(currentLength + " :");
            System.out.println(item.toString());
        }

        //outputting other vending machine choices
        System.out.println(++currentLength + " :" + " Display balance");
        System.out.println(++currentLength + " :" + " Add coins");
        System.out.println(++currentLength + " :" + " Get change");
        System.out.println(++currentLength + " :" + " Exit the vending machine");
        System.out.println("Please enter the number of the choice");

        //returning user choice
        return inputInt(1, items.size() + 4);
    }

    public void outputMoney(BigDecimal currentMoney) {
        //outputting current amount of money in the machine
        System.out.println("You currently have: £" + currentMoney);
    }

    public void outputChange(int[] customerCoins) {

        //outputting the user change
        System.out.println("Your change is:");
        for (int i = 0; i < customerCoins.length; i++) {
            typeOfCoins = coinTypes.values()[i];
            System.out.println(typeOfCoins.toString() + ": " + customerCoins[i]);
        }
    }

    //outputting thank you to the user
    public void goodbye() {
        System.out.println("Thank you for using this Better Best Vending Machine!");
    }
}
