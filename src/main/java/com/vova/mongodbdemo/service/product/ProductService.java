package com.vova.mongodbdemo.service.product;

import com.vova.mongodbdemo.model.product.Product;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product add(Product product);
    Product update(Product product);
    List<Product> findAll();
    Optional<Product> findById(String id);
    Product findByName(String name);
}
