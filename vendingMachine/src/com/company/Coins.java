package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

//creating enums and associated values
enum coinTypes {
    POUND(new BigDecimal("1")),
    FIFTY(new BigDecimal("0.5")),
    TWENTY(new BigDecimal("0.2")),
    TEN(new BigDecimal("0.1")),
    FIVE(new BigDecimal("0.05")),
    TWO(new BigDecimal("0.02")),
    ONE(new BigDecimal("0.01"));

    //constant variable
    private final BigDecimal value;

    //constructor
    coinTypes(BigDecimal value) {
        this.value = value;
    }

    //return the value of a coin
    public BigDecimal getValue() {
        return value;
    }
}

public class Coins {

    coinTypes typeOfCoin;

    //toString method
    @Override
    public String toString() {
        return "Coins{" +
                "typeOfCoin=" + typeOfCoin +
                '}';
    }

    //method to add up all the coins for a total value
    public BigDecimal addUpCoins(int[] differentCoins, BigDecimal currentTotal) {

        //creating a big decimal
        BigDecimal tempTotal = new BigDecimal("0");

        //for loop
        for (int i = 0; i < differentCoins.length; i++) {

            //getting value of coin enum
            typeOfCoin = coinTypes.values()[i];

            //adding to a temp after doing calculation
            tempTotal = tempTotal.add(BigDecimal.valueOf(differentCoins[i]).multiply(typeOfCoin.getValue()));
        }

        //returning current total with the temp total added in
        return currentTotal.add(tempTotal);
    }

    //method to divide the total into coins
    public int[] divideIntoCoins(BigDecimal totalAmountMoney) {

        //creating new variables
        BigDecimal amountCoin;
        MathContext mc = new MathContext(2);
        int[] amountOfCoins = new int[7];

        //looping through array length
        for (int i = 0; i < amountOfCoins.length; i++) {

            //break loop if there is no more money
            if (totalAmountMoney.equals(BigDecimal.valueOf(0))) {
                break;
            }

            //setting enum type
            typeOfCoin = coinTypes.values()[i];

            //to get amount of coin divide total money by the value of the enum
            amountCoin = totalAmountMoney.divide(typeOfCoin.getValue(), mc);

            //adding the amount to the array
            amountOfCoins[i] = amountCoin.intValue();

            //setting the total amount to the remainder of total amount by the coin value
            totalAmountMoney = totalAmountMoney.remainder(typeOfCoin.getValue(), mc);

        }
        //return the array of coins
        return amountOfCoins;
    }

}

