// application/usecases/ReferenceNumberUseCase.java
package com.grupoficohsa.ms_referenceNumber.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.domain.ports.out.ReferenceNumberApiClientPort;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReferenceNumberUseCase {

    private final ReferenceNumberApiClientPort apiClientPort;

    public Mono<ReferenceNumberResponse> execute(HttpHeaders headers, ReferenceNumberRequest request) {
    	log.info("[ID_TRANSACTION:{}] Ejecutando ReferenceNumberUseCase con request={}", headers.getFirst("idTransaction"), request);
        return apiClientPort.getReferenceNumber(headers, request);
    }
}
