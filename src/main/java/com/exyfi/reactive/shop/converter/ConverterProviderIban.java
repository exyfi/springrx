package com.exyfi.reactive.shop.converter;

import com.exyfi.reactive.shop.exception.ReactiveShopException;
import com.exyfi.reactive.shop.model.Currency;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

//TODO reuse this api to load all currency rates and updates it each 30 minutes.
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "currency.api.enabled", havingValue = "true")
public class ConverterProviderIban implements ConverterProvider {

    private static final String URL_PARAMETERS = "?access_key=%s&from=%s&to=%s&amount=%s";

    private final WebClient webClient;

    @Value("${currency.api.url}")
    private String baseUrl;

    @Value("${currency.api.key}")
    private String apiKey;


    public double getConvertedPrice(Currency from, Currency to, double price) {
        final String formedRequest = String.format(URL_PARAMETERS, apiKey, from, to, price);
        ObjectNode body = webClient.post().uri(URI.create(baseUrl + formedRequest)).retrieve().bodyToMono(ObjectNode.class)
                .doOnError(error -> Mono.error(new ReactiveShopException("currency provider returned error:", error)))
                .block();

        if (Objects.isNull(body) || Objects.isNull(body.get("result"))) {
            throw new ReactiveShopException("currency provider returned empty result bla bla bla");
        }

        return body.get("result").asDouble();
    }
}
