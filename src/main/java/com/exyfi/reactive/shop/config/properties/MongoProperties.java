package com.exyfi.reactive.shop.config.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@ConfigurationProperties(prefix = "mongo")
@Slf4j
public class MongoProperties {

    private String url;
    private Integer port;

    @PostConstruct
    private void init() {
        log.info(toString());
    }
}
