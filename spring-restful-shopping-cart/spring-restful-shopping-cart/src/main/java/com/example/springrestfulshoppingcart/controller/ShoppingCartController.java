package com.example.springrestfulshoppingcart.controller;

import com.example.springrestfulshoppingcart.model.CartItem;
import com.example.springrestfulshoppingcart.model.Product;
import com.example.springrestfulshoppingcart.service.ProductService;
import com.example.springrestfulshoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<CartItem> getAllItems() {
        return this.shoppingCartService.getItems();
    }

    @GetMapping("/{itemId}")
    public CartItem getItemById(@PathVariable int itemId) {
        return this.shoppingCartService.getItemById(itemId);
    }


    @PostMapping("/addItem/{productId}")
    public CartItem addItem(@RequestBody CartItem cartItem, @PathVariable int productId) {

        Product getProduct = this.productService.getProductById(productId);
        //判段新增到購物車的數量是否大於商品存貨數量
        if (getProduct.getQuantity() > cartItem.getQuantity()) {
            getProduct.setQuantity(getProduct.getQuantity() - cartItem.getQuantity());
        } else {
            cartItem.setQuantity(getProduct.getQuantity());
            getProduct.setQuantity(0);
        }
        cartItem.setProduct(getProduct);
        return this.shoppingCartService.addItem(cartItem);
    }

    @RequestMapping(value = "/updateItem/{itemId}",method={RequestMethod.DELETE,RequestMethod.PUT})
    public CartItem updateItem(@PathVariable int itemId, @RequestBody CartItem cartItem) {
        //取得欲修改商品資訊
        Product getProduct = this.productService.getProductById(this.shoppingCartService
                .getItemById(itemId).getProduct().getProductId());
        //購物車訂單數量為0直接刪除此訂單
        if (cartItem.getQuantity()<=0){
            return this.shoppingCartService.deleteItem(itemId);

        }else {
            //判段購物車的數量是否大於商品存貨數量
            if (getProduct.getQuantity() > cartItem.getQuantity()) {
                getProduct.setQuantity(getProduct.getQuantity() - cartItem.getQuantity());
            } else {
                cartItem.setQuantity(getProduct.getQuantity());
                getProduct.setQuantity(0);
            }
            return this.shoppingCartService.updateItemQuantity(itemId, cartItem);
        }

    }

    //刪除訂單
    @RequestMapping(value = "/deleteItem/{itemId}",method = RequestMethod.DELETE)
    public CartItem deleteItem(@PathVariable int itemId) {
        return this.shoppingCartService.deleteItem(itemId);
    }
}
