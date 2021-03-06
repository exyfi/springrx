package com.exyfi.reactive.shop.converter;

import com.exyfi.reactive.shop.model.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ConverterProvider.class)
@Slf4j
public class StubConverter implements ConverterProvider {

    @Override
    public double getConvertedPrice(Currency from, Currency to, double price) {
        log.warn("STUB currency converter");
        return 100;
    }
}
