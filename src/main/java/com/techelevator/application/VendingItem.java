package com.techelevator.application;

import java.math.BigDecimal;

public class VendingItem {

    private String vendingId;
    private String candyName;
    private BigDecimal price;
    private int stock = 0;
    private String category;

    public VendingItem(String vendingId, String candyName, String price, String category) {
        this.vendingId = vendingId;
        this.candyName = candyName;
        this.price = new BigDecimal(price);
        this.category = category;
    }
    public void itemPurchased() {
        this.stock--;
    }
    public String getVendingId() {
        return vendingId;
    }

    public String getCandyName() {
        return candyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }
}
