package com.example.springrestfulshoppingcart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {

    private double totalPrice;
    private int quantity;

    private int cartId;
    private Product product;

    public CartItem(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }
}
