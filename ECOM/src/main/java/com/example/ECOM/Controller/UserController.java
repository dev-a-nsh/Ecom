package com.example.ECOM.Controller;

import com.example.ECOM.Service.UserService;
import com.example.ECOM.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")      //working
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser =  userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping                        //working
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")                //working
    public User getUserById(@PathVariable String id) {
        return userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/username/{username}")                 //working
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @DeleteMapping("/{id}")         //working
    public void deleteUser(@PathVariable String id) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}/username")       //working
    public User updateUsername(@PathVariable String id, @RequestParam String newUsername) {
        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(newUsername);
        return userService.registerUser(user); // save changes
    }
}
