package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class VendingMachine 
{
    private static final BigDecimal DOLLAR = new BigDecimal("1.00");
    private static final BigDecimal FIVE_DOLLAR = new BigDecimal("5.00");
    private static final BigDecimal TEN_DOLLAR = new BigDecimal("10.00");
    private static final BigDecimal TWENTY_DOLLAR = new BigDecimal("20.00");
    private static final BigDecimal FIFTY_DOLLAR = new BigDecimal("50.00");
    private static final BigDecimal HUNDRED_DOLLAR = new BigDecimal("100.00");


    public void run() throws FileNotFoundException {
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.parseInventory("catering.csv");
        TransactionAuditor transactionAuditor = new TransactionAuditor();
        boolean inLoop = true;
        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            Scanner scnr = new Scanner(System.in);

            if(choice.equals("display"))
            {
                vendingMachineInventory.displayInventory();
                System.out.println("Enter to return to the previous menu: ");
            }
            else if(choice.equals("purchase"))
            {
                while(inLoop){
                UserOutput.displayPurchaseScreen();
                System.out.println("Current balance: " + vendingMachineInventory.getCurrBalance());
                String purchaseMenuChoice = PurchaseMenu.getPurchaseMenu();
                if (purchaseMenuChoice.equals("select")) {
                    System.out.println("Enter your selection");

                    try {
                        String selectedSlot = scnr.nextLine();
                        VendingItem selectedItem = vendingMachineInventory.searchById(selectedSlot);
                        if (selectedItem.getStock() > 0) {
                            String prevBalance = vendingMachineInventory.getCurrBalance().toString();
                            vendingMachineInventory.purchaseItem(selectedItem);
                            transactionAuditor.audit(selectedItem.getVendingId() + " " + selectedItem.getCandyName(), prevBalance, vendingMachineInventory.getCurrBalance().toString());
                        } else {
                            System.out.println("Item is out of stock! Bringing you back to the Purchase Menu");
                            continue;
                        }
                    } catch (NullPointerException e) {
                        System.out.println("The item you searched for does not exist.  Please try again.");

                        continue;
                    }
                }


                if (purchaseMenuChoice.equals("feed")) {
                    boolean userFeedsMoney = true;
                    //To continuously feed money for the user
                    while (userFeedsMoney) {
                        System.out.println("Please input the amount of added cash: ");
                        try {
                            String moneyFed = scnr.nextLine();
                            vendingMachineInventory.feedMoney(moneyFed);
                            if (new BigDecimal(moneyFed).compareTo(DOLLAR) == 0 || new BigDecimal(moneyFed).compareTo(FIVE_DOLLAR) == 0 || new BigDecimal(moneyFed).compareTo(TEN_DOLLAR) == 0 || new BigDecimal(moneyFed).compareTo(TWENTY_DOLLAR) == 0 || new BigDecimal(moneyFed).compareTo(FIFTY_DOLLAR) == 0 || new BigDecimal(moneyFed).compareTo(HUNDRED_DOLLAR) == 0 ) {
                                transactionAuditor.audit("MONEY FED:", moneyFed, (vendingMachineInventory.getCurrBalance()).toString());
                            }
                            System.out.println("Do you want to add more cash (Enter any character to continue OR 'N' to stop) ?: ");
                            String addMoreOrStop = scnr.nextLine().trim().toLowerCase();
                            if (addMoreOrStop.equals("n")) {
                                userFeedsMoney = false;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("** Please input a Dollar Bill amount! **");
                        }
                    }
                }
                if (purchaseMenuChoice.equals("finish")) {
                    transactionAuditor.audit("CHANGE GIVEN:", (vendingMachineInventory.getCurrBalance()).toString(), "0.00");
                    System.out.println("Here's your change!");
                    vendingMachineInventory.finishTransaction();
                    inLoop = false;
                }
            }
            }
            else if(choice.equals("secret")) {
                vendingMachineInventory.generateSalesReport();
                System.out.println("Sales Report Generated!!!");
            }
            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }
    }
    
}
