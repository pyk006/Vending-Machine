package com.techelevator;

import com.techelevator.application.VendingMachine;
import com.techelevator.application.VendingMachineInventory;

import java.io.FileNotFoundException;

public class VendingMachineCLI 
{
    public static void main(String[] args) throws FileNotFoundException {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.run();
    }
    
}
