package com.exyfi.reactive.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base response for this service.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseResponse {
    /**
     * Success status.
     */
    private boolean success;
    /**
     * Error info.
     */
    private Error error;
}
