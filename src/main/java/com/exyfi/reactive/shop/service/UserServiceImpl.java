package com.exyfi.reactive.shop.service;

import com.exyfi.reactive.shop.converter.CurrencyConverter;
import com.exyfi.reactive.shop.dao.ProductRepository;
import com.exyfi.reactive.shop.dao.UserRepository;
import com.exyfi.reactive.shop.exception.UserNotFoundException;
import com.exyfi.reactive.shop.model.BaseResponse;
import com.exyfi.reactive.shop.model.Currency;
import com.exyfi.reactive.shop.model.product.Product;
import com.exyfi.reactive.shop.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Primary
@Profile("prod")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CurrencyConverter currencyConverter;

    public Mono<User> registerUser(User user) {

        return userRepository.insert(user);
    }

    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    public Mono<BaseResponse> deleteUsers() {
        userRepository.deleteAll();
        return Mono.just(BaseResponse.builder()
                .success(true)
                .build());
    }

    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException(String.format("User by id %s not found", id))));
    }

    public Mono<User> switchCurrencyForUser(Long id, Currency currency) {
        return getUserById(id)
                .map(user -> {
                    user.setId(id);
                    user.setPreferredCurrency(currency);
                    return user;
                })
                .flatMap(userRepository::save);
    }

    public Flux<Product> getProductsForUser(Long id) {
        return getUserById(id)
                .flatMapMany(user -> productRepository.findAll()
                        .map(item -> currencyConverter.convert(user.getPreferredCurrency(), item)));

    }
}
