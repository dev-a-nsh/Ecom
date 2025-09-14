package com.example.ECOM.Service;

import com.example.ECOM.Repo.CartRepository;
import com.example.ECOM.Repo.ProductRepository;
import com.example.ECOM.Repo.UserRepository;
import com.example.ECOM.entity.Cart;
import com.example.ECOM.entity.CartItem;
import com.example.ECOM.entity.Product;
import com.example.ECOM.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findById(String id) {
        return cartRepository.findById(id);
    }

    public void deleteById(String id) {
        cartRepository.deleteById(id);
    }

    public double getTotalPriceByUserId(String userId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id: " + userId));
        return cart.getTotalPrice();
    }

    public Cart addToCart(String userId, String productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findById(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setId(userId);
            newCart.setUserId(userId);
            newCart.setItems(new ArrayList<>());
            newCart.setTotalPrice(0);
            return newCart;
        });

        // Checking if product already in cart
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Increase quantity
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            // Add new CartItem with snapshot info
            CartItem newItem = new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    quantity
            );
            cart.getItems().add(newItem);
        }

        // Recalculating total
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(total);

        return cartRepository.save(cart);
    }
}
