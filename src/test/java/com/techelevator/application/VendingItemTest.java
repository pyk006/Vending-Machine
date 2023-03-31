package com.techelevator.application;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class VendingItemTest {
    private VendingMachineInventory sut;
    @Before
    public void setup () throws FileNotFoundException {
        this.sut = new VendingMachineInventory();
        sut.parseInventory("catering1.csv");

    }
    @Test
    public void itemPurchased_test_with_discount() {
        VendingItem selectedItem = sut.searchByName("snykkers");
        selectedItem.itemPurchased(-1);
        int expectedStock = 5;
        int actualStock = selectedItem.getStock();
        int expectedPurchasedWithDiscount = 1;
        int actualPurchasedWithDiscount = selectedItem.getPurchasedWithDiscount();
        assertEquals(expectedStock, actualStock);
        //Asserts that the discount stock is accounted for
        assertEquals(expectedPurchasedWithDiscount, actualPurchasedWithDiscount);

    }
    @Test
    public void itemPurchased_test_without_discount() {
        VendingItem selectedItem = sut.searchByName("snykkers");
        selectedItem.itemPurchased(1);
        int expectedStock = 5;
        int actualStock = selectedItem.getStock();
        int expectedPurchasedNormal = 1;
        int actualPurchasedNorm = selectedItem.getPurchasedAtFullPrice();
        assertEquals(expectedStock, actualStock);
        //Assers that the normal stock is also accounted for
        assertEquals(expectedPurchasedNormal,actualPurchasedNorm);
    }
    @Test
    public void itemPurchased_test_zero() {
        VendingItem selectedItem = sut.searchByName("snykkers");
        selectedItem.itemPurchased(0);
        int expectedStock = 5;
        int actualStock = selectedItem.getStock();
        int expectedPurchasedNormal = 1;
        int actualPurchasedNorm = selectedItem.getPurchasedAtFullPrice();
        assertEquals(expectedStock, actualStock);
        //Assers that the normal stock is also accounted for
        assertEquals(expectedPurchasedNormal,actualPurchasedNorm);
    }
}