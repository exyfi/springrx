package com.exyfi.reactive.shop.exception;

/**
 * Exception throws in case product not found.
 */
public class ProductNotFoundException extends ReactiveShopException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
