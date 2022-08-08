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
    public List<CartItem> getAllOrders() {
        return this.shoppingCartService.getCartItems();
    }

    @GetMapping("/{CartId}")
    public CartItem getOrderBySeq(@PathVariable int CartId) {
        return this.shoppingCartService.getCartItemBySeq(CartId);
    }


    @PostMapping("/addCartItem/{productId}")
    public CartItem addCartItem(@RequestBody CartItem cartItem, @PathVariable int productId) {

        Product getProduct = this.productService.getProductById(productId);
        //判段新增到購物車的數量是否大於商品存貨數量
        if (getProduct.getQuantity() > cartItem.getQuantity()) {
            getProduct.setQuantity(getProduct.getQuantity() - cartItem.getQuantity());
        } else {
            cartItem.setQuantity(getProduct.getQuantity());
            getProduct.setQuantity(0);
        }
        cartItem.setProduct(getProduct);
        return this.shoppingCartService.addCartItem(cartItem);
    }

    @RequestMapping(value = "/updateOrder/{CartId}",method={RequestMethod.DELETE,RequestMethod.PUT})
    public CartItem updateOrder(@PathVariable int CartId,  @RequestBody CartItem cartItem) {
        //取得欲修改商品資訊
        Product getProduct = this.productService.getProductById(this.shoppingCartService
                .getCartItemBySeq(CartId).getProduct().getProductId());
        //判斷修改數量為0直接刪除此訂單
        if (cartItem.getQuantity()<=0){
            return this.shoppingCartService.deleteItem(CartId);

        }else {
            //判段購物車的數量是否大於商品存貨數量
            if (getProduct.getQuantity() > cartItem.getQuantity()) {
                getProduct.setQuantity(getProduct.getQuantity() - cartItem.getQuantity());
            } else {
                cartItem.setQuantity(getProduct.getQuantity());
                getProduct.setQuantity(0);
            }
            return this.shoppingCartService.updateItemQuantity(CartId, cartItem);
        }

    }

    //刪除訂單
    @RequestMapping(value = "/deleteOrder/{CartId}",method = RequestMethod.DELETE)
    public CartItem deleteOrder(@PathVariable int CartId) {
        return this.shoppingCartService.deleteItem(CartId);
    }
}
