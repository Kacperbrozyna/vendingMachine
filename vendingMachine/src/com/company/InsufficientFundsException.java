package com.company;

public class InsufficientFundsException extends RuntimeException {

    //method to output to console on how to solve exception
    public void HowToRecover() {
        System.out.println("Insufficient amount of money, please add more");
    }
}
