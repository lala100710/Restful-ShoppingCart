package com.example.springrestfulshoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //創建 getter & setter & equals & hashCode & toString 方法
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int productId;

    private String name;

    private double price;

    private String description;

    private int quantity;

    private boolean state=true; //商品上架狀態

    public Product(int productId, String name, double price, String description, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
}
