package com.exyfi.reactive.shop.controller.handler;

import com.exyfi.reactive.shop.exception.ReactiveShopException;
import com.exyfi.reactive.shop.exception.UserNotFoundException;
import com.exyfi.reactive.shop.model.BaseResponse;
import com.exyfi.reactive.shop.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String UNKNOWN_ERROR = "UNKNOWN SERVER ERROR. PLEASE VISIT US LATER";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse> handleUserNotFound(UserNotFoundException e) {
        return new ResponseEntity<>(BaseResponse.builder().error(Error.builder()
                .message(e.getMessage())
                .build()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReactiveShopException.class)
    public ResponseEntity<BaseResponse> handleServiceException(ReactiveShopException e) {
        return new ResponseEntity<>(BaseResponse.builder().error(Error.builder()
                .message(UNKNOWN_ERROR)
                .build()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
