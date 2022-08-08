package com.example.springrestfulshoppingcart.service;

import com.example.springrestfulshoppingcart.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productList;

    public ProductService() {
        this.productList = new ArrayList<>();
        Product product1 = new Product(1, "t-shirt", 100, "black", 250);
        Product product2 = new Product(2, "pants", 150, "yellow", 100);
        this.productList.add(product1);
        this.productList.add(product2);
    }

    public List<Product> getProducts() {

        return this.productList;
    }

    public Product getProductById(int id) {
        for (Product product : this.productList) {
            if (id == product.getProductId())
                return product;
        }
        return null;
    }

    public Product addProduct(Product product) {
        //自動產生商品編號
        LocalDate localDate=LocalDate.now();
        String date=localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        product.setProductId((int)(Math.random()*999)+Integer.parseInt(date));
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
