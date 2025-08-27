package com.grupoficohsa.ms_referenceNumber.infrastructure.webconsumers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.domain.ports.out.ReferenceNumberApiClientPort;
import com.grupoficohsa.ms_referenceNumber.infrastructure.configuration.ReferenceApiProperties;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ReferenceNumberWebClient implements ReferenceNumberApiClientPort {

    private final WebClient.Builder webClientBuilder;
    private final ReferenceApiProperties properties;

    @Override
    public Mono<ReferenceNumberResponse> getReferenceNumber(HttpHeaders headers, ReferenceNumberRequest request) {
        String url = UriComponentsBuilder
                .fromHttpUrl(properties.getUrl())
                .queryParam("targetAudience", request.getTargetAudience())
                .queryParam("userLanguage", request.getUserLanguage())
                .queryParam("agentPartnerId", request.getAgentPartnerId())
                .queryParam("referenceNumber", request.getReferenceNumber())
                .toUriString();

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ReferenceNumberResponse.class);
    }
}
