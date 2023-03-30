package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineInventory {
    //Instance Variables
    private BigDecimal currBalance;
    private int hasDiscount;
    List<VendingItem> inventory = new ArrayList<>();
    LocalDate date;
    LocalDate time;

    public VendingMachineInventory() {

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
        for(VendingItem item : inventory){
            System.out.println(item.getVendingId() + " " + item.getCandyName() + ": " + item.getPrice() + " " + item.getStock());
        }
    }

    public BigDecimal purchaseItem() {
        return new BigDecimal("5");
    }
    public BigDecimal feedMoney(String inputVal) {
        return this.currBalance.add(new BigDecimal(inputVal));
    }
}
