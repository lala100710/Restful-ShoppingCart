package com.example.springrestfulshoppingcart.service;

import com.example.springrestfulshoppingcart.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {
    private List<CartItem> cartItemList;

    public ShoppingCartService() {
        this.cartItemList = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return this.cartItemList;
    }

    public CartItem addItem(CartItem cartItem) {
        cartItem.setItemId(cartItem.generateItemId());
        this.cartItemList.add(cartItem);
        return cartItem;
    }

    public CartItem getItemById(int id) {
        for (CartItem cartItem : this.cartItemList) {
            if (id == cartItem.getItemId()) {
                return cartItem;
            }
        }
        return null;
    }

    public CartItem updateItemQuantity(int seq, CartItem cartItem) {
        for (CartItem updatedCartItem : this.cartItemList) {
            if (seq == updatedCartItem.getItemId()) {
                updatedCartItem.setQuantity(cartItem.getQuantity());
                return updatedCartItem;
            }
        }
        return null;
    }

    public CartItem deleteItem(int seq) {
        for (CartItem cartItem : this.cartItemList) {
            if (seq == cartItem.getItemId()) {
                this.cartItemList.remove(cartItem);
            }
            return cartItem;
        }
        return null;
    }

}
