package com.example.springrestfulshoppingcart.controller;

import com.example.springrestfulshoppingcart.model.Product;
import com.example.springrestfulshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getAllProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return this.productService.getProductById(id);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        product.setProductId(product.generateId());
        return this.productService.addProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable int id) {
        return this.productService.updateProduct(product, id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public Product deleteProduct(@PathVariable int id) {
        return this.productService.deleteProduct(id);
    }
}
