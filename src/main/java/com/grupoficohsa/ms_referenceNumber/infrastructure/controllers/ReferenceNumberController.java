package com.grupoficohsa.ms_referenceNumber.infrastructure.controllers;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.application.usecases.ReferenceNumberUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Slf4j
public class ReferenceNumberController {

    private final ReferenceNumberUseCase useCase;
   
    @GetMapping("/{referenceNumber}")
    public Mono<ResponseEntity<ReferenceNumberResponse>> getTransactionByReferenceNumber(
            @RequestHeader(name = "idTransaction", required = true) String idTransaction,
            @PathVariable("referenceNumber") String referenceNumber,
            @RequestHeader HttpHeaders headers
    ) {
        log.info("[ID_TRANSACTION:{}] INICIO GET /transactions/{}", idTransaction, referenceNumber);

        ReferenceNumberRequest request = ReferenceNumberRequest.builder()
                .referenceNumber(referenceNumber)
                .build();

        headers.set("idTransaction", idTransaction);

        return useCase.execute(headers, request)
                .map(ResponseEntity::ok)
                .doOnSuccess(resp ->
                        log.info("[ID_TRANSACTION:{}] FIN GET /transactions/{} -> OK",
                                idTransaction, referenceNumber))
                .doOnError(error ->
                        log.error("[ID_TRANSACTION:{}] Error en GET /transactions/{} -> {}",
                                idTransaction, referenceNumber, error.getMessage(), error));
    }
}
