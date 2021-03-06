package com.exyfi.reactive.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents business error.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    /**
     * Error message.
     */
    private String message;
}
