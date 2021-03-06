package com.exyfi.reactive.shop.service;

import com.exyfi.reactive.shop.model.Currency;
import com.exyfi.reactive.shop.model.product.Product;
import com.exyfi.reactive.shop.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface to handle users operations like registration, updating.
 */
public interface UserService {

    /**
     * Register user.
     *
     * @param user user info
     * @return registered user
     */
    Mono<User> registerUser(User user);

    /**
     * Get all users.
     *
     * @return users
     */
    Flux<User> getUsers();

    /**
     * Get user with provided id.
     *
     * @param id requested user id
     * @return user associated with given id
     */
    Mono<User> getUserById(Long id);

    /**
     * Update currency for given user.
     *
     * @param id       user id
     * @param currency new currency
     * @return updated user
     */
    Mono<User> switchCurrencyForUser(Long id, Currency currency);

    /**
     * Shows products to user in user currency.
     *
     * @param id user id
     * @return products
     */
    Flux<Product> getProductsForUser(Long id);
}
