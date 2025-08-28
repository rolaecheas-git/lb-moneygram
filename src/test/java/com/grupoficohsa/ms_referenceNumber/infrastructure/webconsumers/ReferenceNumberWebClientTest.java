package com.grupoficohsa.ms_referenceNumber.infrastructure.webconsumers;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse.Money;
import com.grupoficohsa.ms_referenceNumber.application.response.ReferenceNumberResponse.SendAmount;
import com.grupoficohsa.ms_referenceNumber.infrastructure.configuration.ReferenceApiProperties;
import com.grupoficohsa.ms_referenceNumber.infrastructure.configuration.TokenApiProperties;
import com.grupoficohsa.webclient.component.WebClientComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ReferenceNumberWebClientTest {

    @Mock
    private WebClientComponent webClientComponent;

    @Mock
    private ReferenceApiProperties referenceApiProperties;

    @Mock
    private TokenApiProperties tokenApiProperties;

    @Mock
    private TokenApiProperties.Service tokenService;

    @InjectMocks
    private ReferenceNumberWebClient webClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(referenceApiProperties.getUrlApi()).thenReturn("https://api.test/reference");
        when(tokenApiProperties.getClientId()).thenReturn("clientIdTest");
        when(tokenApiProperties.getClientToken()).thenReturn("clientTokenTest");
        when(tokenApiProperties.getService()).thenReturn(tokenService);
        when(tokenService.getUrlToken()).thenReturn("https://api.test/token");
    }

    @Test
    void getReferenceNumber_shouldCallWebClientComponent_andReturnResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("idTransaction", "12345");

        ReferenceNumberRequest request = ReferenceNumberRequest.builder()
                .referenceNumber("REF123")
                .build();

        // --- Construir un response ficticio ---
        ReferenceNumberResponse expectedResponse = new ReferenceNumberResponse();
        expectedResponse.setTransactionId("TXN-001");
        expectedResponse.setReferenceNumber("REF123");
        expectedResponse.setTransactionStatus("COMPLETED");

        // Construir sendAmount con un valor
        SendAmount sendAmount = new SendAmount();
        Money amount = new Money();
        amount.setValue(BigDecimal.valueOf(500));
        amount.setCurrencyCode("USD");
        sendAmount.setAmount(amount);
        expectedResponse.setSendAmount(sendAmount);

        when(webClientComponent.post(
                eq("https://api.test/reference"),
                eq("https://api.test/token"),
                any(Map.class),
                any(Map.class),
                eq(headers),
                eq(ReferenceNumberResponse.class)
        )).thenReturn(Mono.just(expectedResponse));

        // --- Ejecutar ---
        Mono<ReferenceNumberResponse> result = webClient.getReferenceNumber(headers, request);

        // --- Verificar ---
        StepVerifier.create(result)
                .assertNext(resp -> {
                    assertThat(resp.getTransactionId()).isEqualTo("TXN-001");
                    assertThat(resp.getReferenceNumber()).isEqualTo("REF123");
                    assertThat(resp.getTransactionStatus()).isEqualTo("COMPLETED");
                    assertThat(resp.getSendAmount().getAmount().getValue())
                            .isEqualByComparingTo(BigDecimal.valueOf(500));
                    assertThat(resp.getSendAmount().getAmount().getCurrencyCode()).isEqualTo("USD");
                })
                .verifyComplete();

        verify(webClientComponent).post(
                eq("https://api.test/reference"),
                eq("https://api.test/token"),
                argThat(params ->
                        "REF123".equals(params.get("referenceNumber")) // 👈 sigue validando el query param
                ),
                argThat(tokenParams ->
                        "clientIdTest".equals(tokenParams.get("clientId")) &&
                        "clientTokenTest".equals(tokenParams.get("clientToken"))
                ),
                eq(headers),
                eq(ReferenceNumberResponse.class)
        );
    }

    @Test
    void getReferenceNumber_shouldHandleWebClientError() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("idTransaction", "errorCase");

        ReferenceNumberRequest request = ReferenceNumberRequest.builder()
                .referenceNumber("REF123")
                .build();

        when(webClientComponent.post(
                anyString(), anyString(), anyMap(), anyMap(), any(HttpHeaders.class), eq(ReferenceNumberResponse.class)
        )).thenReturn(Mono.error(new RuntimeException("Simulated error")));

        Mono<ReferenceNumberResponse> result = webClient.getReferenceNumber(headers, request);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException
                        && throwable.getMessage().equals("Simulated error"))
                .verify();
    }
}
