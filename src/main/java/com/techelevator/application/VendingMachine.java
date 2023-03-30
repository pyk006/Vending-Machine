package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachine 
{

<<<<<<< HEAD
    public void run() throws FileNotFoundException{
=======

    public void run() throws FileNotFoundException {
>>>>>>> main
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.parseInventory();

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
                    vendingMachineInventory.purchaseItem(selectedItem);
                }
                if (purchaseMenuChoice.equals("feed")) {
<<<<<<< HEAD
//                    user.feedMoney();
=======
                    System.out.println("Please input the amount of added cash: ");
                    String moneyFed = scnr.nextLine();
                    vendingMachineInventory.feedMoney(moneyFed);
>>>>>>> main
                }
                if (purchaseMenuChoice.equals("finish"));
            }
            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }
    }
    
}
