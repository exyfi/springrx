package com.exyfi.reactive.shop.service;

import com.exyfi.reactive.shop.dao.ProductRepository;
import com.exyfi.reactive.shop.exception.ProductNotFoundException;
import com.exyfi.reactive.shop.model.BaseResponse;
import com.exyfi.reactive.shop.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
@Profile("prod")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Mono<Product> insertProduct(Product product) {
        log.debug("adding new product to the shop. product name={}", product.getName());
        return productRepository.insert(product);
    }

    @Override
    public Flux<Product> getAllProducts() {
        log.debug("get all products");
        return productRepository.findAll();
    }

    public Mono<Product> getProductById(Long id) {
        log.debug("get product by id={}", id);
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(String.format("Product by id %s not found", id))));
    }

    public Mono<BaseResponse> deleteProduct(Long id) {
        log.debug("removing product by given id={}", id);
        productRepository.deleteById(id);

        return Mono.just(BaseResponse.builder()
                .success(true)
                .build());
    }

}
