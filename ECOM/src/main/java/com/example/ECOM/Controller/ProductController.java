package com.example.ECOM.Controller;

import com.example.ECOM.Service.ProductService;
import com.example.ECOM.Service.UserService;
import com.example.ECOM.entity.Product;
import com.example.ECOM.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.pattern.PathPattern;

import java.util.List;

//efefefe
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping    //Working
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping     //Working
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")    //working
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @DeleteMapping("/{id}")     //working
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/category/{category}")    //Working
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.findByCategory(category);
    }

}
