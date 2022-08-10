package com.example.springrestfulshoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data //創建 getter & setter & equals & hashCode & toString 方法
@NoArgsConstructor
public class Product {

    private int productId;

    private String name;

    private double price;

    private String description;

    private int quantity;

    private boolean state=true; //商品上架狀態

    public Product( String name, double price, String description, int quantity) {
        this.productId= generateId();
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public int generateId(){
        //自動產生商品編號
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        return (int) (Math.random() * 999) + Integer.parseInt(date);
    }

}
