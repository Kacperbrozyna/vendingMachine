package com.company;

import java.math.BigDecimal;

public class vendingMachineItems {

    String name;
    BigDecimal price;
    int numbOfItems;

    //constructor
    public vendingMachineItems(String name, BigDecimal price, int numbOfItems) {
        this.name = name;
        this.price = price;
        this.numbOfItems = numbOfItems;
    }

    //toString method
    @Override
    public String toString() {
        return
                "Item = " + name + "\n" +
                        "Price = " + price + "\n" +
                        "numbOfItems = " + numbOfItems + "\n";
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumbOfItems() {
        return numbOfItems;
    }

    public void setNumbOfItems(int numbOfItems) {
        this.numbOfItems = numbOfItems;
    }
}
