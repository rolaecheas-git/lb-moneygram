package com.grupoficohsa.ms_referenceNumber.domain.services;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestInfoServiceTest {

    @Test
    void process_returnsSameRequest() {
        RequestInfoService service = new RequestInfoService();

        ReferenceNumberRequest request = ReferenceNumberRequest.builder()
                .targetAudience("AGENT_FACING")
                .userLanguage("EN-US")
                .agentPartnerId("30150519")
                .referenceNumber("123456")
                .build();

        ReferenceNumberRequest result = service.process(request);

        assertThat(result).isEqualTo(request);
    }
}
