package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> main
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineInventory {
    //Instance Variables
    private BigDecimal currBalance;
    private int hasDiscount;
    List<VendingItem> inventory = new ArrayList<>();
<<<<<<< HEAD
    LocalDate date;
    LocalDate time;
=======
    private BigDecimal currBalance = new BigDecimal(0.00);
    private int hasDiscount = 1;
>>>>>>> main

    public VendingMachineInventory() {

    }
    public VendingItem searchById(String s) {
        for(VendingItem item : inventory) {
            if(item.getVendingId().equals(s)) return item;
        }
        return null;
    }
    public VendingItem searchByName(String s) {
        for(VendingItem item: inventory) {
            if(item.getCandyName().equals(s)) return item;
        }
        return null;
    }

    public void parseInventory() throws FileNotFoundException {
        File cateringFile = new File("catering.csv");
        Scanner scnr = new Scanner(cateringFile);

        while(scnr.hasNext()) {
            String[] oneItem = scnr.nextLine().split(",");
            inventory.add(new VendingItem(oneItem[0], oneItem[1], oneItem[2], oneItem[3]));
        }
    }

    public void displayInventory() {
        for(VendingItem item : inventory) {
            System.out.println(item.getVendingId() + " " + item.getCandyName() + ": " + item.getPrice() + " " + item.getStock());
        }
    }
<<<<<<< HEAD

    public BigDecimal purchaseItem() {
        return new BigDecimal("5");
=======
    //TODO: potentially write custom exception in items to throw out when no stock left.
    public void purchaseItem(String s) {
        searchById(s).itemPurchased();
        if(hasDiscount < 0) {
            this.currBalance.add(new BigDecimal(1));
        }
        this.currBalance.subtract(searchById(s).getPrice());

>>>>>>> main
    }
    public BigDecimal feedMoney(String inputVal) {
        return this.currBalance.add(new BigDecimal(inputVal));
    }
}
