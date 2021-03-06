package com.exyfi.reactive.shop.model.product;

import com.exyfi.reactive.shop.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;

/**
 * Class represents Product model.
 */
@Document(collection = "productcollection")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    /**
     * Product id.
     */
    @Id
    private Long id;
    /**
     * Product name.
     */
    @Nonnull
    private String name;
    /**
     * Product price.
     */
    private double price;
    /**
     * Currency for price.
     */
    @Nonnull
    private Currency currency;
}
