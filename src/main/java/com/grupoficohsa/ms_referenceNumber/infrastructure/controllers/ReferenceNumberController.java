package com.grupoficohsa.ms_referenceNumber.infrastructure.controllers;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.application.usecases.ReferenceNumberUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
@Slf4j
public class ReferenceNumberController {

    private final ReferenceNumberUseCase useCase;

    @GetMapping
    public Mono<ReferenceNumberResponse> getReferenceNumber(
            @RequestHeader HttpHeaders headers,
            ReferenceNumberRequest request) {

        String idTransaction = headers.getFirst("idTransaction");

        if (idTransaction == null || idTransaction.isBlank()) {
            log.error("Header 'idTransaction' is missing or empty. No se puede procesar la transacción.");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Header 'idTransaction' is required"
            );
        }

        log.info("[ID_TRANSACTION:{}]  INICIO METODO referenceNumber", idTransaction);

        log.debug("[ID_TRANSACTION:{}] Request recibido -> {}", idTransaction, request);

        headers.set("idTransaction", idTransaction);

        return useCase.execute(headers, request)
                .doOnSuccess(response -> 
                        log.info("[ID_TRANSACTION:{}] FIN METODO referenceNumber. Respuesta OK -> {}", 
                                 idTransaction, response))
                .doOnError(error -> 
                        log.error("[ID_TRANSACTION:{}] Error en metodo referenceNumber -> {}", 
                                  idTransaction, error.getMessage(), error));
    }
}
