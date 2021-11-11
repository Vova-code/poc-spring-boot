package com.vova.mongodbdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Data
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private ArrayList<String> imageUrl;
    private String category;
    private Long price;
    private int stock;

    public Product(String name, String description, ArrayList<String> imageUrl, String category, Long price, int stock) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format(
                "Product: [id=%s, name=%s, description=%s, imageUrls=%s, category=%s, price=%d, stock=%d]",
                this.id, this.name, this.description, this.imageUrl, this.category, this.price, this.stock
        );
    }
}


