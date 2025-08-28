package com.grupoficohsa.ms_referenceNumber.infrastructure.exceptions.models;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@JsonPropertyOrder({ "statusCode", "status", "message", "data", "timestamp", "transactionId" })
public class ErrorResponse {
    private String statusCode;
    private String status;
    private String message;
    private ErrorData data;
    private String timestamp;
    private String transactionId;

    @Data
    @Builder
    public static class ErrorData {
        private ErrorDetails errorDetails;
    }

    @Data
    @Builder
    public static class ErrorDetails {
        private String code;
        private Map<String, Object> fields;
    }
}
