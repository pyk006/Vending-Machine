package com.techelevator.application;

import com.techelevator.application.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineInventoryTest {
    private VendingMachineInventory sut;
    @Before
    public void setUp() throws Exception {
        sut = new VendingMachineInventory();
        sut.parseInventory("catering1.csv");

    }
    @Test
    public void parseInventoryTest_with_catering1_file() throws FileNotFoundException {
        sut.parseInventory("catering1.csv");
        VendingItem expectedValue = new VendingItem("D1", "Chippos", "3.85", "Munchy");
        VendingItem actualValue = sut.getInventory().get(3);

        assertEquals(expectedValue.getPrice(),actualValue.getPrice());
        assertEquals(expectedValue.getCandyName(),actualValue.getCandyName());

    }
    @Test
    public void parseInventoryTest_with_catering_file() throws FileNotFoundException {
        sut.parseInventory("catering.csv");
        VendingItem expectedValue = new VendingItem("A1", "U-Chews", "1.65", "Gum");
        VendingItem actualValue = sut.getInventory().get(0);

        assertEquals(expectedValue.getPrice(),actualValue.getPrice());
        assertEquals(expectedValue.getCandyName(),actualValue.getCandyName());
        assertEquals(expectedValue.getCategory(),actualValue.getCategory());

    }
    @Test
    public void searchByIdTest_getCandyName() {
        String s = "A4";
        String expectedValue = "Teaberry";
        String actualValue = sut.searchById(s).getCandyName();
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void searchByIdTest_getVendingId() {
        String s = "A4";
        String expectedValue = "A4";
        String actualValue = sut.searchById(s).getVendingId();
        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void searchByNameTest() {
        String s = "popcorn";
        String expectedValue = "Popcorn";
        String actualValue = sut.searchByName(s).getCandyName();
        assertEquals(expectedValue,actualValue);

    }



    @Test
    public void displayInventoryTest_stock() {
        sut.displayInventory();
        System.out.println("Go to catering1 file and see if info matches ^^^ lol");
;
    }


    @Test
    public void purchaseItem_NotMoney() throws FileNotFoundException {
        /*
        Arrange:

         */
        sut.parseInventory("catering.csv");
        sut.feedMoney("1.00");
        VendingItem purchasedItem = sut.searchById("A1");
        sut.purchaseItem(purchasedItem);
        System.out.println("Should say you do not have money!");
    }

    @Test
    public void purchaseItem_DiscountCheck() throws FileNotFoundException {
        sut.parseInventory("catering.csv");
        sut.feedMoney("5.00");
        VendingItem canAffordTwoWithDiscount = sut.searchById("C3");
        sut.purchaseItem(canAffordTwoWithDiscount);
        System.out.println(sut.getCurrBalance());
        System.out.println(canAffordTwoWithDiscount.getPrice());
        System.out.println("Purchasing C3 again, should only dispense if discount is working.");
        sut.purchaseItem(canAffordTwoWithDiscount);

    }

    @Test
    public void purchaseItem_ExactValue() throws FileNotFoundException {
        sut.parseInventory("catering.csv");
        sut.feedMoney("10.00");
        sut.feedMoney("1.00");
        sut.feedMoney("1.00");
        sut.feedMoney("1.00");

        VendingItem stupidItem = sut.searchById("C2");
        sut.purchaseItem(stupidItem);
        sut.purchaseItem(stupidItem);
        sut.purchaseItem(stupidItem);
        sut.purchaseItem(stupidItem);
        System.out.println("Last purchase price == currBalance will only dispense 4th time if comparison logic is correct.");

    }

    @Test
    public void purchaseItem_OutOfStock() {
        sut.feedMoney("20.00");
        VendingItem repeatedPurchase = sut.searchById("a1");
        System.out.println("buying item until out of stock");
        sut.purchaseItem(repeatedPurchase);
        sut.purchaseItem(repeatedPurchase);
        sut.purchaseItem(repeatedPurchase);
        sut.purchaseItem(repeatedPurchase);
        sut.purchaseItem(repeatedPurchase);
        sut.purchaseItem(repeatedPurchase);
        System.out.println("Next attempt should return out of stock");
        sut.purchaseItem(repeatedPurchase);
    }


    @Test
    public void feedMoneyTest_normal_dollar_value() {
        String moneyVal = "5.00";
        BigDecimal expectedValueOfBalance = new BigDecimal("5.00");
        sut.feedMoney(moneyVal);
        assertEquals(expectedValueOfBalance, sut.getCurrBalance());
    }
    @Test
    public void feedMoneyTest_irregular_dollar_amount() {
        String moneyVal = "5.20";
        BigDecimal expectedValueOfBalance = new BigDecimal("0.00");
        sut.feedMoney(moneyVal);
        assertEquals(expectedValueOfBalance, sut.getCurrBalance());
    }

    @Test
    public void finishTransaction_change_calc_value() {
        sut.feedMoney("5.00");
        VendingItem selectedItem = sut.searchById("A4");
        sut.purchaseItem(selectedItem);
        String expectedValue = "3 Dollars 1 Quarters 1 Dimes ";
        String actualValue = sut.finishTransaction();
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void finishTransaction_balance_to_zero() {
        sut.feedMoney("5.00");
        VendingItem selectedItem = sut.searchById("A2");
        sut.purchaseItem(selectedItem);
        sut.finishTransaction();
        BigDecimal expectedValue = new BigDecimal("0.00");
        BigDecimal actualValue = sut.getCurrBalance();
        assertEquals(expectedValue, actualValue);
    }
}