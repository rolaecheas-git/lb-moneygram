package com.grupoficohsa.ms_referenceNumber.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "token")
public class TokenApiProperties {
    private String clientId;
    private String clientToken;
    private Service service;

    @Data
    public static class Service {
        private String urlToken;
    }
}
