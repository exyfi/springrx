package com.exyfi.reactive.shop.controller;

import com.exyfi.reactive.shop.model.Currency;
import com.exyfi.reactive.shop.model.product.Product;
import com.exyfi.reactive.shop.model.user.User;
import com.exyfi.reactive.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Mono<User> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("user/{id}")
    public Mono<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("user/{id}/currency")
    public Mono<User> switchCurrency(@PathVariable("id") Long id, @RequestParam Currency currency) {
        return userService.switchCurrencyForUser(id, currency);
    }

    @GetMapping("user/{id}/products")
    public Flux<Product> getUsersProducts(@PathVariable("id") Long id) {
        return userService.getProductsForUser(id);
    }
}
