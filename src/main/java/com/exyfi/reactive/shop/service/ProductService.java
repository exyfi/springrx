package com.exyfi.reactive.shop.service;

import com.exyfi.reactive.shop.model.BaseResponse;
import com.exyfi.reactive.shop.model.product.Product;
import reactor.core.publisher.Mono;

/**
 * Service for operations with products.
 */
public interface ProductService {
    /**
     * Add new product to the shop.
     *
     * @param product product info
     * @return this product
     */
    Mono<Product> insertProduct(Product product);

    /**
     * Returns product by given id.
     *
     * @param id product id
     * @return product
     */
    Mono<Product> getProductById(Long id);

    /**
     * Delete product by given id.
     *
     * @param id product id
     * @return information of success
     */
    Mono<BaseResponse> deleteProduct(Long id);
}
