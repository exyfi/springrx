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

    private boolean success;
    private Error error;
}
