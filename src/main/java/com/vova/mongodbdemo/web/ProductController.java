package com.vova.mongodbdemo.web;

import com.vova.mongodbdemo.model.Product;
import com.vova.mongodbdemo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {

        Product savedProduct = productService.add(product);
        String savedProductId = savedProduct.getId();

        if (productService.findById(savedProductId).isPresent()) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/id/{id}")
                    .buildAndExpand(savedProduct.getId())
                    .toUri();

            System.out.println(location);
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.internalServerError().build();
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

    @GetMapping("/hello")
    public ResponseEntity<Integer> hello(HttpSession session) {
        Integer counter = productService.retrieveSession(session);

        return ResponseEntity.ok(counter);
    }

}
