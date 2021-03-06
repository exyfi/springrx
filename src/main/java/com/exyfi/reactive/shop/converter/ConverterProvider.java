package com.exyfi.reactive.shop.converter;

import com.exyfi.reactive.shop.model.Currency;

/**
 * Converter from product currency to user currency for given price.
 */
public interface ConverterProvider {

    /**
     * Convert price for target currency.
     *
     * @param from  product source currency
     * @param to    user preferred currency
     * @param price actual product price for product currency
     * @return price in user preferred currency
     */
    double getConvertedPrice(Currency from, Currency to, double price);
}
