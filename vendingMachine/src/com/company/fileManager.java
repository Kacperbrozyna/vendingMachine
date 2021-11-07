package com.company;

import java.math.BigDecimal;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class fileManager {

    //creating collections
    private ArrayList<vendingMachineItems> fileVendingMachineItems = new ArrayList<vendingMachineItems>();
    private ArrayList<vendingMachineItems> unavailableVendingMachineItems;
    private ArrayList<vendingMachineItems> availableVendingMachineItems;

    //getters
    public ArrayList<vendingMachineItems> getUnavailableVendingMachineItems() {
        return unavailableVendingMachineItems;
    }

    public ArrayList<vendingMachineItems> getAvailableVendingMachineItems() {
        return availableVendingMachineItems;
    }

    //setters
    public void setUnavailableVendingMachineItems(ArrayList<vendingMachineItems> unavailableVendingMachineItems) {
        this.unavailableVendingMachineItems = unavailableVendingMachineItems;
    }

    public void setAvailableVendingMachineItems(ArrayList<vendingMachineItems> availableVendingMachineItems) {
        this.availableVendingMachineItems = availableVendingMachineItems;
    }

    //method to read the file
    public void readFile() {

        //creating objects and containers
        File filename = new File("vendingMachineItems.txt");
        FileReader file = null;
        String[] values;

        //try catch block to read in file
        try {

            //setting default values
            String name = "N/A";
            BigDecimal price = new BigDecimal("1.0");
            int quantityOfItems = 0;

            //creating new objects
            //new file reader and scanner
            file = new FileReader(filename);
            Scanner in = new Scanner(file);

            //while keep reading if there is another line
            while (in.hasNext()) {

                //reading in the line and splitting it
                values = in.nextLine().split(",");

                switch (values.length) {
                    case 3:
                        quantityOfItems = Integer.parseInt(values[2]);
                    case 2:
                        price = new BigDecimal(values[1]);
                    case 1:
                        name = values[0];
                    default:
                        break;
                }

                // creates a new item and adds it to collection
                vendingMachineItems fileItem = new vendingMachineItems(name, price, quantityOfItems);
                fileVendingMachineItems.add(fileItem);
            }

            //closes the file after reading
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(fileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ae) {
            Logger.getLogger(fileManager.class.getName()).log(Level.SEVERE, null, ae);
        }

        //using streams to filter items
        availableVendingMachineItems = fileVendingMachineItems.stream()
                .filter(p -> p.getNumbOfItems() > 0)
                .collect(Collectors.toCollection(ArrayList::new));

        unavailableVendingMachineItems = fileVendingMachineItems.stream()
                .filter(p -> p.getNumbOfItems() == 0)
                .collect(Collectors.toCollection(ArrayList::new));

    }


    //method to write to file
    public void writeFile() {

        //setting an iterator and the name of the output file
        File fileNameOut = new File("outputVendingMachineItems.txt");
        fileVendingMachineItems.clear();
        fileVendingMachineItems = availableVendingMachineItems;
        fileVendingMachineItems.addAll(unavailableVendingMachineItems);

        //try catch when writing to file
        try {
            //creating a writer, buffered and file
            FileWriter fw = new FileWriter(fileNameOut);
            BufferedWriter bw = new BufferedWriter(fw);

            //while loop, if the iterator can go along more
            for (vendingMachineItems fileVendingMachineItem : fileVendingMachineItems) {
                //getting the next entry in the collection and writes it to the file
                bw.write(fileVendingMachineItem.name + "," + fileVendingMachineItem.price + "," + fileVendingMachineItem.numbOfItems + "\n");
            }
            //flushing and closing the output file
            bw.flush();
            bw.close();

        } catch (Exception e) {
            Logger.getLogger(fileManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
