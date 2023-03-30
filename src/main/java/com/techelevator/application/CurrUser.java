package com.techelevator.application;

import java.math.BigDecimal;

public class CurrUser {
    //Instance Variables
    private BigDecimal currBalance;
    private int hasDiscount;

    public BigDecimal purchaseItem() {
        return new BigDecimal("5");
    }
    public BigDecimal feedMoney(String inputVal) {
        return this.currBalance.add(new BigDecimal(inputVal));
    }

}
