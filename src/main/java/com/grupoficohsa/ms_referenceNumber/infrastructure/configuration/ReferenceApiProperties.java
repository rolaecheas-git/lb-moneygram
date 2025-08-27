package com.grupoficohsa.ms_referenceNumber.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "reference.service")
public class ReferenceApiProperties {
    private String url;
}
