package com.vova.mongodbdemo.dao.product;

import com.vova.mongodbdemo.model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends MongoRepository<Product, String> {

    Product insert(Product product);
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(String id);
    Product findByName(String name);
}
