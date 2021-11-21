package com.vova.mongodbdemo.web;

import com.vova.mongodbdemo.model.product.Product;
import com.vova.mongodbdemo.service.product.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Optional<Product>> addProduct(@RequestBody Product product) {

        try {
            Product savedProduct = productService.add(product);

            return ResponseEntity.status(CREATED).body(productService.findById(savedProduct.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Optional<Product> retrievedProduct = productService.findById(product.getId());

        if (retrievedProduct.isPresent()) {
            Product updateProduct = productService.update(product);
            return ResponseEntity.status(CREATED).body(updateProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> retrieveProductByName(@PathVariable String name) {
        Optional<Product> retrievedProduct = Optional.ofNullable(productService.findByName(name));

        if (retrievedProduct.isPresent()) {
            return ResponseEntity.status(OK).body(retrievedProduct);
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> retrieveProductById(@PathVariable String id) {
        Optional<Product> retrievedProduct = productService.findById(id);

        if (retrievedProduct.isPresent()) {
            return ResponseEntity.status(OK).body(retrievedProduct);
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

}
