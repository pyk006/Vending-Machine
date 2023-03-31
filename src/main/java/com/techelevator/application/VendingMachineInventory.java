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
        //TODO: figure out a solution for the null return here keeping this here but solved above ^
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


    public String purchaseItem(String s) {

        if (hasDiscount > 0 && (this.currBalance.compareTo(searchById(s).getPrice()) == 1 || this.currBalance.compareTo(searchById(s).getPrice()) == 0) && searchById(s).getStock() > 0) {
            searchById(s).itemPurchased();
            this.currBalance = this.currBalance.subtract(searchById(s).getPrice());
            hasDiscount = -hasDiscount;
            //Dispensing string
            //TODO: potentially make this into a submethod that we call in purchaseItem()
            if (searchById(s).getCategory().equals("Munchy")) {
                return "Item Dispensing..." + searchById(s).getVendingId() + " " +  searchById(s).getCandyName() + " Munchy, Munchy, so Good!";
            }
            if (searchById(s).getCategory().equals("Candy")) {
                return "Item Dispensing..."  + searchById(s).getVendingId() + " " +  searchById(s).getCandyName() +  " Sugar, Sugar, so Sweet!";
            }
            if (searchById(s).getCategory().equals("Drink")) {
                return "Item Dispensing..."  + searchById(s).getVendingId() + " " +  searchById(s).getCandyName() +  " Drinky, Drinky, Slurp Slurp!";
            }
            if (searchById(s).getCategory().equals("Gum")) {
                return "Item Dispensing..."  + searchById(s).getVendingId() + " " +  searchById(s).getCandyName() +  " Chewy, Chewy, Lots O Bubbles!";
            }
            return "";
        }
        else if (hasDiscount < 0 && (this.currBalance.compareTo(searchById(s).getPrice().subtract(new BigDecimal("1.00"))) == 1 || this.currBalance.compareTo(searchById(s).getPrice().subtract(new BigDecimal("1.00"))) == 0) && searchById(s).getStock() > 0) {
            searchById(s).itemPurchased();
                this.currBalance = this.currBalance.add(new BigDecimal("1.00"));
                hasDiscount = Math.abs(hasDiscount);

            this.currBalance = this.currBalance.subtract(searchById(s).getPrice());
            //Dispensing string
            if (searchById(s).getCategory().equals("Munchy")) {
                return "Item Dispensing..." + searchById(s).getVendingId() + " " +  searchById(s).getCandyName() + " Munchy, Munchy, so Good!";
            }
            if (searchById(s).getCategory().equals("Candy")) {
                return "Item Dispensing..."  + searchById(s).getVendingId() + " " +  searchById(s).getCandyName() +  " Sugar, Sugar, so Sweet!";
            }
            if (searchById(s).getCategory().equals("Drink")) {
                return "Item Dispensing..."  + searchById(s).getVendingId() + " " +  searchById(s).getCandyName() +  " Drinky, Drinky, Slurp Slurp!";
            }
            if (searchById(s).getCategory().equals("Gum")) {
                return "Item Dispensing..."  + searchById(s).getVendingId() + " " +  searchById(s).getCandyName() +  " Chewy, Chewy, Lots O Bubbles!";
            }
            return "";
        } else {
            //logic to check if purchase failed because of no money or no stock
            return this.currBalance.compareTo(searchById(s).getPrice()) == -1 ? "You don't have enough money!" : "Item out of stock!";
        }
    }
    public void feedMoney(String inputVal) {

        if (new BigDecimal(inputVal).compareTo(new BigDecimal("1.00")) == 0 || new BigDecimal(inputVal).compareTo(new BigDecimal("5.00")) == 0 || new BigDecimal(inputVal).compareTo(new BigDecimal("10.00")) == 0 || new BigDecimal(inputVal).compareTo(new BigDecimal("20.00")) == 0 || new BigDecimal(inputVal).compareTo(new BigDecimal("100.00")) == 0 ) {
            this.currBalance = this.currBalance.add(new BigDecimal(inputVal));
            System.out.println("***** CURRENT MONEY PROVIDED: " + this.currBalance + " *****");
        } else {
            System.out.println("Please put in Dollar Bills only!");
        }
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
