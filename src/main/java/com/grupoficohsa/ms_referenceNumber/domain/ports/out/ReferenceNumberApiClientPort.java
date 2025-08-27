package com.grupoficohsa.ms_referenceNumber.domain.ports.out;

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;

public interface ReferenceNumberApiClientPort {
    Mono<ReferenceNumberResponse> getReferenceNumber(HttpHeaders headers, ReferenceNumberRequest request);
}
