package com.grupoficohsa.ms_referenceNumber.infrastructure.controllers;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.application.usecases.ReferenceNumberUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReferenceNumberControllerTest {

    @Mock
    private ReferenceNumberUseCase useCase;

    @InjectMocks
    private ReferenceNumberController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTransactionByReferenceNumber_shouldReturnResponse_whenIdTransactionPresent() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("idTransaction", "12345");

        String referenceNumber = "REF123";

        ReferenceNumberResponse expectedResponse = new ReferenceNumberResponse();
        expectedResponse.setDestinationCountryCode("HN");

        when(useCase.execute(any(HttpHeaders.class), any(ReferenceNumberRequest.class)))
                .thenReturn(Mono.just(expectedResponse));

        Mono<ResponseEntity<ReferenceNumberResponse>> result =
                controller.getTransactionByReferenceNumber("12345", referenceNumber, headers);

        StepVerifier.create(result)
                .assertNext(resp -> {
                    assertThat(resp.getStatusCode().is2xxSuccessful()).isTrue();
                    assertThat(resp.getBody()).isNotNull();
                    assertThat(resp.getBody().getDestinationCountryCode()).isEqualTo("HN");
                })
                .verifyComplete();

        verify(useCase).execute(any(HttpHeaders.class), any(ReferenceNumberRequest.class));
    }

    @Test
    void getTransactionByReferenceNumber_shouldPropagateErrorFromUseCase() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("idTransaction", "12345");

        String referenceNumber = "REF123";
        RuntimeException simulatedError = new RuntimeException("Simulated downstream error");

        when(useCase.execute(any(HttpHeaders.class), any(ReferenceNumberRequest.class)))
                .thenReturn(Mono.error(simulatedError));

        Mono<ResponseEntity<ReferenceNumberResponse>> result =
                controller.getTransactionByReferenceNumber("12345", referenceNumber, headers);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Simulated downstream error"))
                .verify();

        verify(useCase).execute(any(HttpHeaders.class), any(ReferenceNumberRequest.class));
    }
}
