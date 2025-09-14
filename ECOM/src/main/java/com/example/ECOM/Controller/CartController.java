package com.example.ECOM.Controller;


import com.example.ECOM.Service.CartService;
import com.example.ECOM.entity.AddToCartRequest;
import com.example.ECOM.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // Adding a product to cart
    @PostMapping("/add")                //WORKING FINALLY
    public Cart addToCart(@RequestBody AddToCartRequest request) {
        return cartService.addToCart(request.getUserId(), request.getProductId(), request.getQuantity());
    }

    @GetMapping                         //WORKING
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{userId}")
    public Cart getCartById(@PathVariable String userId) {
        return cartService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @GetMapping("/total/{userId}")
    public double getTotalPrice(@PathVariable String userId) {
        return cartService.getTotalPriceByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable String id) {
        cartService.deleteById(id);
    }
}
