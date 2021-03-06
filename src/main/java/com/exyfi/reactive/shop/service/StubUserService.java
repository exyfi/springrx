package com.exyfi.reactive.shop.service;

import com.exyfi.reactive.shop.model.Currency;
import com.exyfi.reactive.shop.model.product.Product;
import com.exyfi.reactive.shop.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Profile("dev")
public class StubUserService implements UserService {

    @Override
    public Mono<User> registerUser(User user) {
        log.warn("STUB register user operation");
        return Mono.just(new User(1L, "kek", Currency.EUR));
    }

    @Override
    public Flux<User> getUsers() {
        log.warn("STUB get users operation");
        return Flux.just(new User(1L, "kek", Currency.EUR), new User(2L, "keks", Currency.RUB));
    }

    @Override
    public Mono<User> getUserById(Long id) {
        log.warn("STUB get user by id operation");
        return Mono.just(new User(id, "ekrem", Currency.RUB));
    }

    @Override
    public Mono<User> switchCurrencyForUser(Long id, Currency currency) {
        log.warn("STUB switch currency for user operation");
        return Mono.just(new User(id, "lll", currency));
    }

    @Override
    public Flux<Product> getProductsForUser(Long id) {
        log.warn("STUB get products for user");
        return Flux.just(Product.builder()
                .id(1L)
                .price(1)
                .name("AA")
                .currency(Currency.EUR)
                .build());
    }
}
