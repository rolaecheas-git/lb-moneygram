package com.grupoficohsa.ms_referenceNumber.application.usecases;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse.Money;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse.SendAmount;
import com.grupoficohsa.ms_referenceNumber.domain.ports.out.ReferenceNumberApiClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ReferenceNumberUseCaseTest {

    private ReferenceNumberApiClientPort apiClientPort;
    private ReferenceNumberUseCase useCase;

    @BeforeEach
    void setUp() {
        apiClientPort = Mockito.mock(ReferenceNumberApiClientPort.class);
        useCase = new ReferenceNumberUseCase(apiClientPort);
    }

    @Test
    void execute_returnsResponse_whenApiClientSucceeds() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("idTransaction", "12345");

        ReferenceNumberRequest request = new ReferenceNumberRequest(
                "AGENT_FACING",
                "EN-US",
                "30150519",
                "999999"
        );

        ReferenceNumberResponse response = new ReferenceNumberResponse();
        response.setTransactionId("TXN-001");
        response.setReferenceNumber("999999");
        response.setTransactionStatus("COMPLETED");

        SendAmount sendAmount = new SendAmount();
        Money amount = new Money();
        amount.setValue(BigDecimal.valueOf(250));
        amount.setCurrencyCode("USD");
        sendAmount.setAmount(amount);

        response.setSendAmount(sendAmount);

        Mockito.when(apiClientPort.getReferenceNumber(headers, request))
                .thenReturn(Mono.just(response));

        // --- Ejecutar ---
        StepVerifier.create(useCase.execute(headers, request))
                .assertNext(resp -> {
                    assertThat(resp.getTransactionId()).isEqualTo("TXN-001");
                    assertThat(resp.getReferenceNumber()).isEqualTo("999999");
                    assertThat(resp.getTransactionStatus()).isEqualTo("COMPLETED");
                    assertThat(resp.getSendAmount().getAmount().getValue()).isEqualByComparingTo(BigDecimal.valueOf(250));
                    assertThat(resp.getSendAmount().getAmount().getCurrencyCode()).isEqualTo("USD");
                })
                .verifyComplete();

        Mockito.verify(apiClientPort).getReferenceNumber(headers, request);
    }

    @Test
    void execute_propagatesError_whenApiClientFails() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("idTransaction", "12345");

        ReferenceNumberRequest request = new ReferenceNumberRequest(
                "AGENT_FACING",
                "EN-US",
                "30150519",
                "999999"
        );

        RuntimeException simulatedError = new RuntimeException("API call failed");

        Mockito.when(apiClientPort.getReferenceNumber(headers, request))
                .thenReturn(Mono.error(simulatedError));

        StepVerifier.create(useCase.execute(headers, request))
                .expectErrorMatches(throwable ->
                        throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("API call failed")
                )
                .verify();

        Mockito.verify(apiClientPort).getReferenceNumber(headers, request);
    }
}
