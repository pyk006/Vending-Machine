package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.FileNotFoundException;

public class VendingMachine 
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
                // make a purchase
            }
            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }
    }
    
}
