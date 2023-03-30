package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineInventory {
    //Instance Variables
    private List<VendingItem> inventory = new ArrayList<>();
    private static final BigDecimal NICKEL = new BigDecimal(".05");
    private static final BigDecimal DIME = new BigDecimal(".10");
    private static final BigDecimal QUARTER = new BigDecimal(".25");
    private static final BigDecimal PENNY = new BigDecimal(".01");
    private static final BigDecimal DOLLAR = new BigDecimal("1.00");
    private static final BigDecimal ZERO = new BigDecimal("0.00");
    private BigDecimal currBalance = new BigDecimal("0.00");
    private int hasDiscount = 1;


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

    //TODO: potentially write custom exception in items to throw out when no stock left.
    public void purchaseItem(String s) {
        searchById(s).itemPurchased();
        if(hasDiscount < 0) {
            this.currBalance.add(new BigDecimal("1.00"));
        }
        this.currBalance.subtract(searchById(s).getPrice());

    }
    public BigDecimal feedMoney(String inputVal) {
        return this.currBalance.add(new BigDecimal(inputVal));
    }

    public String finishTransaction() {
        String changeOutput = "";
        BigDecimal amountOfDollars = this.currBalance.divide(DOLLAR);
        BigDecimal amountOfQuarters = this.currBalance.remainder(DOLLAR).divide(QUARTER);
        BigDecimal amountOfDimes = this.currBalance.remainder(DOLLAR).remainder(QUARTER).divide(DIME);
        BigDecimal amountOfNickels = this.currBalance.remainder(DOLLAR).remainder(QUARTER).remainder(DIME).divide(NICKEL);
        BigDecimal amountOfPennies = this.currBalance.remainder(DOLLAR).remainder(QUARTER).remainder(DIME).remainder(NICKEL).divide(PENNY);
        if (amountOfDollars.compareTo(ZERO) == 1) {
            changeOutput += amountOfDollars + "Dollars ";
        }
        if (amountOfQuarters.compareTo(ZERO) == 1) {
            changeOutput += amountOfQuarters + "Quarters ";
        }
        if (amountOfDimes.compareTo(ZERO) == 1) {
            changeOutput += amountOfDimes + "Dimes ";
        }
        if (amountOfNickels.compareTo(ZERO) == 1) {
            changeOutput += amountOfNickels + "Nickels ";
        }
        if (amountOfPennies.compareTo(ZERO) == 1) {
            changeOutput += amountOfPennies + "Pennies ";
        }

            return changeOutput;

    }
}
