package com.exyfi.reactive.shop.exception;

/**
 * Base exception of this service.
 */
public class ReactiveShopException extends RuntimeException {
    public ReactiveShopException(String message) {
        super(message);
    }

    public ReactiveShopException(String message, Throwable cause) {
        super(message, cause);
    }
}
