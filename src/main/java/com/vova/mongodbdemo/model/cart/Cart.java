package com.vova.mongodbdemo.model.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private String customerId;
    private List<String> productList;

    public void addToCart(String productId) {
        productList.add(productId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return customerId.equals(cart.customerId) && Objects.equals(productList, cart.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productList);
    }
}
