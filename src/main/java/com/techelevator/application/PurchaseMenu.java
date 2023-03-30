package com.techelevator.application;

import java.util.Scanner;

public class PurchaseMenu {

    public static String getPurchaseMenu() {
        // Scanner variable
         Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("S) Select Items");
        System.out.println("M) Feed Money");
        System.out.println("F) Finish Transaction");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        if (option.equals("s"))
        {
            return "select";
        }
        else if (option.equals("m"))
        {
            return "feed";
        }
        else if (option.equals("f"))
        {
            return "finish";
        }
        return "";
    }
}
