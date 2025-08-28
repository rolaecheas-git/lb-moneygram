package com.grupoficohsa.ms_referenceNumber.infrastructure.webconsumers;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.domain.ports.out.ReferenceNumberApiClientPort;
import com.grupoficohsa.ms_referenceNumber.infrastructure.configuration.ReferenceApiProperties;
import com.grupoficohsa.ms_referenceNumber.infrastructure.configuration.TokenApiProperties;
import com.grupoficohsa.ms_referenceNumber.infrastructure.controllers.ReferenceNumberController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import  com.grupoficohsa.webclient.component.WebClientComponent;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReferenceNumberWebClient implements ReferenceNumberApiClientPort {

    private final WebClientComponent webClientComponent; 
    private final ReferenceApiProperties properties;
    private final TokenApiProperties tokenProps;
    @Override
    public Mono<ReferenceNumberResponse> getReferenceNumber(HttpHeaders headers, ReferenceNumberRequest request) {
        String idTransaction = headers.getFirst("idTransaction");

        log.info("[ID_TRANSACTION:{}] Preparando llamada a API externa con URL={}", idTransaction, properties.getUrlApi());

        Map<String, String> queryParams = Map.of(
            "targetAudience", request.getTargetAudience(),
            "userLanguage", request.getUserLanguage(),
            "agentPartnerId", request.getAgentPartnerId(),
            "referenceNumber", request.getReferenceNumber()
        );
        
        Map<String, String> tokenParams = new HashMap<>();
        tokenParams.put("clientId",    tokenProps.getClientId());
        tokenParams.put("clientToken", tokenProps.getClientToken());
        log.info("[ID_TRANSACTION:{}] Preparando componentes para getToken: urlGetToken={}, clientId={}",
        		idTransaction, tokenProps.getService().getUrlToken(), tokenProps.getClientId());
        
        
        return webClientComponent.post(
                    properties.getUrlApi(),
                    tokenProps.getService().getUrlToken(), 
                    queryParams,
                    tokenParams,
                    headers,
                    ReferenceNumberResponse.class
            )
            .doOnSuccess(response -> 
                log.info("[ID_TRANSACTION:{}] Llamada a API externa enviada correctamente. Respuesta OK", idTransaction)
            )
            .doOnError(error -> 
                log.error("[ID_TRANSACTION:{}] Error en llamada a API externa -> {}", idTransaction, error.getMessage())
            );
    }

}
