package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class VendingMachine 
{



    public void run() throws FileNotFoundException {
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.parseInventory();
        TransactionAuditor transactionAuditor = new TransactionAuditor();

        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            Scanner scnr = new Scanner(System.in);

            if(choice.equals("display"))
            {
                vendingMachineInventory.displayInventory();
            }
            else if(choice.equals("purchase"))
            {
                UserOutput.displayPurchaseScreen();
                String purchaseMenuChoice = PurchaseMenu.getPurchaseMenu();
                if (purchaseMenuChoice.equals("select")) {
                    System.out.println("Enter your selection");
                    String selectedItem = scnr.nextLine();
                    if (vendingMachineInventory.searchById(selectedItem).getStock() > 0) {
                        String prevBalance = vendingMachineInventory.getCurrBalance().toString();
                        System.out.println(vendingMachineInventory.purchaseItem(selectedItem));
                        transactionAuditor.audit(vendingMachineInventory.searchById(selectedItem).getVendingId() + " " + vendingMachineInventory.searchById(selectedItem).getCandyName(), prevBalance, vendingMachineInventory.getCurrBalance().toString());
                    } else {
                        System.out.println("Item is out of stock! Bringing you back to the Purchase Menu");
                        UserOutput.displayPurchaseScreen();
                        purchaseMenuChoice = PurchaseMenu.getPurchaseMenu();
                    }
                }
                if (purchaseMenuChoice.equals("feed")) {
                    boolean userFeedsMoney = true;
                    //To continuously feed money for the user
                    while (userFeedsMoney) {
                        System.out.println("Please input the amount of added cash: ");
                        String moneyFed = scnr.nextLine();
                        vendingMachineInventory.feedMoney(moneyFed);
                        transactionAuditor.audit("MONEY FED:", moneyFed, (vendingMachineInventory.getCurrBalance()).toString());
                        System.out.println("Do you want to add more cash (Enter any character to continue OR 'N' to stop) ?: ");
                        String addMoreOrStop = scnr.nextLine().trim().toLowerCase();
                        if (addMoreOrStop.equals("n")) {
                            userFeedsMoney = false;
                        }

                    }
                }
                if (purchaseMenuChoice.equals("finish")) {
                    transactionAuditor.audit("CHANGE GIVEN:", (vendingMachineInventory.getCurrBalance()).toString(), "0.00");
                    System.out.println("Here's your change!");
                    vendingMachineInventory.finishTransaction();

                }
            }
            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }
    }
    
}
