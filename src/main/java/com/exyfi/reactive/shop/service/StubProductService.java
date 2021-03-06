package com.exyfi.reactive.shop.service;

import com.exyfi.reactive.shop.model.BaseResponse;
import com.exyfi.reactive.shop.model.Currency;
import com.exyfi.reactive.shop.model.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Profile("dev")
public class StubProductService implements ProductService {
    @Override
    public Mono<Product> insertProduct(Product product) {
        log.warn("STUB operation insert product");
        return Mono.just(Product.builder()
                .id(1L)
                .name("AAA")
                .currency(Currency.EUR)
                .price(100)
                .build());
    }

    @Override
    public Mono<Product> getProductById(Long id) {
        log.warn("STUB operation get product by id");
        return Mono.just(Product.builder()
                .id(id)
                .currency(Currency.EUR)
                .name("Book Java Concurrency in Practice")
                .price(50)
                .build());
    }

    @Override
    public Mono<BaseResponse> deleteProduct(Long id) {
        log.warn("STUB operation delete product");
        return Mono.just(BaseResponse.builder()
                .success(true)
                .build());
    }
}
