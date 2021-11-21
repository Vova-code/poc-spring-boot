package com.vova.mongodbdemo.service.product;

import com.vova.mongodbdemo.dao.product.ProductDao;
import com.vova.mongodbdemo.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product add(Product product) {
        return productDao.insert(product);
    }

    @Override
    public Product update(Product product) {
        return productDao.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }
}
