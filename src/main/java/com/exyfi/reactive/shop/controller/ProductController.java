package com.exyfi.reactive.shop.controller;

import com.exyfi.reactive.shop.model.BaseResponse;
import com.exyfi.reactive.shop.model.product.Product;
import com.exyfi.reactive.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public Mono<Product> addNewProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}/delete")
    public Mono<BaseResponse> deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
}
