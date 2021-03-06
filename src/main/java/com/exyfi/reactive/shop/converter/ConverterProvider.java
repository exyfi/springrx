package com.exyfi.reactive.shop.converter;

import com.exyfi.reactive.shop.model.Currency;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//TODO reuse this api to load all currency rates and updates it each 30 minutes.
@Component
@RequiredArgsConstructor
public class ConverterProvider {

    private static final String URL_PARAMETERS = "?access_key=%s&from=%s&to=%s&amount=%s";

    private final RestTemplate restTemplate;

    @Value("${currency.api.url}")
    private String baseUrl;

    @Value("${currency.api.key}")
    private String apiKey;


    public double getConvertedPrice(Currency from, Currency to, double price) {
        final String formedRequest = String.format(URL_PARAMETERS, apiKey, from, to, price);
        ObjectNode body = restTemplate.postForEntity(baseUrl + formedRequest, null, ObjectNode.class).getBody();
        return body.get("result").asDouble();
    }
}
