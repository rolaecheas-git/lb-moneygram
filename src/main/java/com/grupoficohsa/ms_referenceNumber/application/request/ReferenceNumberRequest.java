package com.grupoficohsa.ms_referenceNumber.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceNumberRequest {
    private String targetAudience;
    private String userLanguage;
    private String agentPartnerId;
    private String referenceNumber;
}
