package com.exyfi.reactive.shop.model.user;

import com.exyfi.reactive.shop.model.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;

/**
 * Class represents User model.
 */
@Document(collection = "usercollection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * User id.
     */
    @Id
    private Long id;
    /**
     * User login.
     */
    @Nonnull
    @JsonProperty("login")
    private String login;
    /**
     * User preferred currency. Provides at the time of registration.
     */
    @Nonnull
    @JsonProperty("preferredCurrency")
    private Currency preferredCurrency;
}
