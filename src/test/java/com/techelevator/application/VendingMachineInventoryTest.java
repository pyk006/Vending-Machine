package com.techelevator.application;

import com.techelevator.application.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class VendingMachineInventoryTest {
    private VendingMachineInventory sut;
    @Before
    public void setUp() throws Exception {
        sut = new VendingMachineInventory();
        sut.parseInventory();
        File testFile = new File("catering1.csv");

    }
    @Test
    public void parseInventoryTest() throws FileNotFoundException {

    }
    @Test
    public void searchByIdTest_getCandyName() {
        String s = "A4";
        String expectedValue = "Chippos";
        String actualValue = sut.searchById(s).getCandyName();
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void searchByIdTest_item_object() {
        String s = "A5";
        String expectedValue = "Chippos";
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
    public void feedMoneyTest() {
    }

    @Test
    public void finishTransaction() {
    }
}