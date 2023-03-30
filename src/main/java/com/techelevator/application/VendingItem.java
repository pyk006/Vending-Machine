package com.techelevator.application;

import java.math.BigDecimal;

public class VendingItem {
<<<<<<< HEAD

=======
    private String vendingId;
    private String candyName;
    private BigDecimal price;
    private int stock = 6;
    private String category;

    public VendingItem(String vendingId, String candyName, String price, String category) {
        this.vendingId = vendingId;
        this.candyName = candyName;
        this.price = new BigDecimal(price);
        this.category = category;
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
>>>>>>> main
}
