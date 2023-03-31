package com.techelevator.application;

import java.math.BigDecimal;

public class VendingItem {

    private String vendingId;
    private String candyName;
    private BigDecimal price;
    private int stock = 6;
    private String category;
    private int purchasedAtFullPrice = 0;
    private int purchasedWithDiscount = 0;



    public VendingItem(String vendingId, String candyName, String price, String category) {
        this.vendingId = vendingId;
        this.candyName = candyName;
        this.price = new BigDecimal(price);
        this.category = category;
    }

    public void itemPurchased(int i) {
        this.stock--;
        if(i < 0) purchasedWithDiscount++;
        else  purchasedAtFullPrice++;
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
    public int getPurchasedAtFullPrice() {
        return purchasedAtFullPrice;
    }

    public int getPurchasedWithDiscount() {
        return purchasedWithDiscount;
    }
}
