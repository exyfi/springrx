package com.exyfi.reactive.shop.converter;

import com.exyfi.reactive.shop.model.Currency;
import com.exyfi.reactive.shop.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyConverter {

    private final ConverterProvider converterProvider;

    public Product convert(Currency userCurrency, Product product) {
        double convertedPrice = converterProvider.getConvertedPrice(product.getCurrency(), userCurrency, product.getPrice());

        return new Product(product.getId(), product.getName(), convertedPrice, userCurrency);
    }
}
