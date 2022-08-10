package com.example.springrestfulshoppingcart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class CartItem {

    private double totalPrice;

    private int quantity;

    private int ItemId;

    private Product product;

    public CartItem(int quantity) {
        this.ItemId = generateItemId();
        this.quantity = quantity;
    }

    public int generateItemId() {
        //自動產生訂單編號
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        return (int) (Math.random() * 999) + Integer.parseInt(date);
    }

    public double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }
}
