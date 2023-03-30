package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.FileNotFoundException;

public class VendingMachine 
{

    private CurrUser user = new CurrUser();
    public void run()
    {

    public void run() throws FileNotFoundException {
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.parseInventory();

        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
                vendingMachineInventory.displayInventory();
            }
            else if(choice.equals("purchase"))
            {
                UserOutput.displayPurchaseScreen();
                String purchaseMenuChoice = PurchaseMenu.getPurchaseMenu();
                if (purchaseMenuChoice.equals("select")) {

                }
                if (purchaseMenuChoice.equals("feed")) {
                    user.feedMoney();
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
