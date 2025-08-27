// infrastructure/controllers/ReferenceNumberController.java
package com.grupoficohsa.ms_referenceNumber.infrastructure.controllers;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.application.usecases.ReferenceNumberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceNumberController {

    private final ReferenceNumberUseCase useCase;

    @GetMapping
    public Mono<ReferenceNumberResponse> getReferenceNumber(
            @RequestHeader HttpHeaders headers,
            ReferenceNumberRequest request) {

        return useCase.execute(headers, request); // ahora devuelve un Mono
    }
}
