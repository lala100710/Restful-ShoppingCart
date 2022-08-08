package com.example.springrestfulshoppingcart.service;

import com.example.springrestfulshoppingcart.model.CartItem;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {
    private List<CartItem> cartItemList;

    public ShoppingCartService() {
        this.cartItemList = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return this.cartItemList;
    }

    public CartItem addCartItem(CartItem cartItem) {
        //自動產生訂單編號
        LocalDate localDate=LocalDate.now();
        String date=localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        cartItem.setCartId((int)(Math.random()*999)+Integer.parseInt(date));
        this.cartItemList.add(cartItem);
        return cartItem;
    }

    public CartItem getCartItemBySeq(int seq) {
        for (CartItem cartItem : this.cartItemList) {
            if (seq == cartItem.getCartId()) {
                return cartItem;
            }
        }
        return null;
    }

    public CartItem updateItemQuantity(int seq, CartItem cartItem) {
        for (CartItem updatedCartItem : this.cartItemList) {
            if (seq == updatedCartItem.getCartId()) {
                updatedCartItem.setQuantity(cartItem.getQuantity());
                return updatedCartItem;
            }
        }
        return null;
    }

    public CartItem deleteItem(int seq) {
        for (CartItem cartItem : this.cartItemList) {
            if (seq == cartItem.getCartId()) {
                this.cartItemList.remove(cartItem);
            }
            return cartItem;
        }
        return null;
    }

}
