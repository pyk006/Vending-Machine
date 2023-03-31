package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        s = s.toLowerCase(Locale.ROOT);
        for(VendingItem item : inventory) {
            if(item.getVendingId().toLowerCase(Locale.ROOT).equals(s)) return item;
        }
        //System.out.println("Sorry no such item exists.");
        throw new NullPointerException();
    }
    public VendingItem searchByName(String s) {
        //TODO: figure out a solution for the null return here
        for(VendingItem item: inventory) {
            if(item.getCandyName().toLowerCase(Locale.ROOT).equals(s)) return item;
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
            String itemStock = item.getStock() > 0 ? "" + item.getStock() : "NO LONGER AVAILABLE";
            System.out.println(item.getVendingId() + " " + item.getCandyName() + ": " + item.getPrice() + " " + itemStock);
        }
    }

    public BigDecimal getCurrBalance() {
        return currBalance;
    }

    //TODO: potentially write custom exception in items to throw out when no stock left.
    public void purchaseItem(VendingItem itemToPurchase) {

        if (hasDiscount > 0 && (this.currBalance.compareTo(itemToPurchase.getPrice()) == 1 || this.currBalance.compareTo(itemToPurchase.getPrice()) == 0) && itemToPurchase.getStock() > 0) {
            itemToPurchase.itemPurchased();
            this.currBalance = this.currBalance.subtract(itemToPurchase.getPrice());
            hasDiscount = -hasDiscount;
            //Dispensing string
            dispenseMessage(itemToPurchase);
        }
        else if (hasDiscount < 0 && (this.currBalance.compareTo(itemToPurchase.getPrice().subtract(new BigDecimal("1.00"))) == 1 || this.currBalance.compareTo(itemToPurchase.getPrice().subtract(new BigDecimal("1.00"))) == 0) && itemToPurchase.getStock() > 0) {
            itemToPurchase.itemPurchased();
                this.currBalance = this.currBalance.add(new BigDecimal("1.00"));
                hasDiscount = Math.abs(hasDiscount);

            this.currBalance = this.currBalance.subtract(itemToPurchase.getPrice());
            //Dispensing string
            //dispenseMessage
            dispenseMessage(itemToPurchase);
        } else {
            if(this.currBalance.compareTo(itemToPurchase.getPrice()) == -1) System.out.println("You do not have enough money!");
            else System.out.println("Item out of stock!");
            //logic to check if purchase failed because of no money or no stock
            //this.currBalance.compareTo(searchById(s).getPrice()) == -1 ? "You don't have enough money!" : "Item out of stock!";
        }
    }
    public void dispenseMessage(VendingItem item) {
        if (item.getCategory().equals("Munchy")) {
            System.out.println("Item Dispensing..." + item.getVendingId() + " " +  item.getCandyName() + " Munchy, Munchy, so Good!");
        }
        if (item.getCategory().equals("Candy")) {
            System.out.println("Item Dispensing..."  + item.getVendingId() + " " +  item.getCandyName() +  " Sugar, Sugar, so Sweet!");
        }
        if (item.getCategory().equals("Drink")) {
            System.out.println("Item Dispensing..."  + item.getVendingId() + " " +  item.getCandyName() +  " Drinky, Drinky, Slurp Slurp!");
        }
        if (item.getCategory().equals("Gum")) {
            System.out.println("Item Dispensing..."  + item.getVendingId() + " " +  item.getCandyName() +  " Chewy, Chewy, Lots O Bubbles!");
        }
    }
    public void feedMoney(String inputVal) {
        this.currBalance = this.currBalance.add(new BigDecimal(inputVal));
        System.out.println("***** CURRENT MONEY PROVIDED: " + this.currBalance + " *****");
    }

    public String finishTransaction() {
        String changeOutput = "";
        int amountOfDollars = (this.currBalance.divide(DOLLAR)).intValue();
        int amountOfQuarters = (this.currBalance.remainder(DOLLAR).divide(QUARTER)).intValue();
        int amountOfDimes = (this.currBalance.remainder(DOLLAR).remainder(QUARTER).divide(DIME)).intValue();
        int amountOfNickels = (this.currBalance.remainder(DOLLAR).remainder(QUARTER).remainder(DIME).divide(NICKEL)).intValue();
        int amountOfPennies = (this.currBalance.remainder(DOLLAR).remainder(QUARTER).remainder(DIME).remainder(NICKEL).divide(PENNY)).intValue();
        if (amountOfDollars > 0) {
            changeOutput += amountOfDollars + " Dollars ";
        }
        if (amountOfQuarters > 0) {
            changeOutput += amountOfQuarters + " Quarters ";
        }
        if (amountOfDimes > 0) {
            changeOutput += amountOfDimes + " Dimes ";
        }
        if (amountOfNickels>0) {
            changeOutput += amountOfNickels + " Nickels ";
        }
        if (amountOfPennies>0) {
            changeOutput += amountOfPennies + " Pennies ";
        }
        this.currBalance = ZERO;
        System.out.println(changeOutput);
            return changeOutput;

    }
}
