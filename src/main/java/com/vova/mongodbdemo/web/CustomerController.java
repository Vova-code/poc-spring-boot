package com.vova.mongodbdemo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vova.mongodbdemo.dao.sessions.SessionDao;
import com.vova.mongodbdemo.model.cart.Cart;
import com.vova.mongodbdemo.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "localhost", maxAge = 3600)
public class CustomerController {

    ObjectMapper mapper = new ObjectMapper();

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Object> signin(HttpSession session, HttpServletRequest request) throws JsonProcessingException {
        List<String> sessionIds = HeaderHttpSessionIdResolver.xAuthToken().resolveSessionIds(request);

        if (!sessionIds.isEmpty() && request.getSession().getAttribute("cart") != null) {
            HttpSession updatedSession = request.getSession();

            return ResponseEntity.ok(
                    mapper.readValue(
                            updatedSession.getAttribute("cart").toString(), Cart.class
                    )
            );
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Cart> addToBasket(@PathVariable String productId, HttpServletRequest request) throws JsonProcessingException {
        HttpSession requestSession = request.getSession();

        if (requestSession != null) {

            if (requestSession.getAttribute("cart") != null){
                Cart updatedCart = mapper.readValue(requestSession.getAttribute("cart").toString(), Cart.class);
                requestSession.setAttribute("cart", mapper.writeValueAsString(updatedCart));
            } else {
                requestSession.setAttribute("cart", mapper.writeValueAsString(new Cart("1", Collections.singletonList(productId))));
            }

            return ResponseEntity.ok(
                    mapper.readValue(
                            requestSession.getAttribute("cart").toString(), Cart.class
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
