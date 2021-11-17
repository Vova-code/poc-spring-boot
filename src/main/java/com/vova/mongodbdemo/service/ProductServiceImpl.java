package com.vova.mongodbdemo.service;

import com.vova.mongodbdemo.dao.ProductDao;
import com.vova.mongodbdemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

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

    @Override
    public Integer retrieveSession(HttpSession session) {
        Integer counter = (Integer) session.getAttribute("counter");
        session.setMaxInactiveInterval(30);

        if (counter == null) {
            counter = 1;
        } else {
            counter += 1;
        }

        session.setAttribute("counter", counter);
        return counter;
    }
}
