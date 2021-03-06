package com.exyfi.reactive.shop.exception;

/**
 * Exception throws in case user not found.
 */
public class UserNotFoundException extends ReactiveShopException{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
