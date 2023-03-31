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
    public void parseInventoryTest() throws FileNotFoundException {

    }
    @Test
    public void searchByIdTest_getCandyName() {
        String s = "A4";
        String expectedValue = "Teaberry";
        String actualValue = sut.searchById(s).getCandyName();
        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void searchByNameTest() {

    }



    @Test
    public void displayInventoryTest() {
    }

    @Test
    public void getCurrBalanceTest() {
    }

    @Test
    public void purchaseItemTest() {
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
    public void finishTransaction_balance_should_be_zero() throws FileNotFoundException {
        sut.feedMoney("5.00");
        VendingItem selectedItem = sut.searchById("A4");
        sut.purchaseItem(selectedItem);
        String expectedValue = "3 Dollars 1 Quarters 1 Dimes ";
        String actualValue = sut.finishTransaction();
        assertEquals(expectedValue, actualValue);
    }
}