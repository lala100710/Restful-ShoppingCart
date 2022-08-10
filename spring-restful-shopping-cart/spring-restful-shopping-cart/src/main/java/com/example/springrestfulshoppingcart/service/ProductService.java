package com.example.springrestfulshoppingcart.service;

import com.example.springrestfulshoppingcart.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productList;

    public ProductService() {
        this.productList = new ArrayList<>();
        Product product1 = new Product("t-shirt", 100, "black", 250);
        Product product2 = new Product("pants", 150, "yellow", 100);
        this.productList.add(product1);
        this.productList.add(product2);
    }

    public List<Product> getProducts() {
        List<Product> checkProductList = new ArrayList<>();
        for (Product product : this.productList) {
            if (product.isState())
                checkProductList.add(product);
        }
        return checkProductList;
    }

    public Product getProductById(int id) {
        for (Product product : this.productList) {
            if (id == product.getProductId()) {
                if (product.isState())
                    return product;
            }
        }
        return null;
    }

    public Product addProduct(Product product) {
        this.productList.add(product);
        return product;
    }

    public Product updateProduct(Product product, int id) {
        for (Product updatedProduct : this.productList) {
            //只修改某修欄位
            if (id == updatedProduct.getProductId()) {
                if (product.getName() != null && !product.getName().isEmpty()) {
                    updatedProduct.setName(product.getName());
                }
                if (product.getQuantity() > 0) {
                    updatedProduct.setQuantity(product.getQuantity());
                    updatedProduct.setState(true);
                } else {
                    updatedProduct.setQuantity(0);
                    updatedProduct.setState(false);
                }
                if (product.getDescription() != null && !product.getDescription().isEmpty()) {
                    updatedProduct.setDescription(product.getDescription());
                }
                if (product.getPrice() > 0) {
                    updatedProduct.setPrice(product.getPrice());
                }
                return updatedProduct;
            }
        }
        return null;
    }

    public Product deleteProduct(int id) {
        for (Product product : this.productList) {
            if (id == product.getProductId()) {
                this.productList.remove(product);
                return product;
            }
        }
        return null;
    }
}
