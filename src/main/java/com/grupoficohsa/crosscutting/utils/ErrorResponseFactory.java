package com.grupoficohsa.crosscutting.utils;

import com.grupoficohsa.crosscutting.constants.ErrorCatalog;
import com.grupoficohsa.ms_referenceNumber.infrastructure.exceptions.models.ErrorResponse;

import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.Map;

public class ErrorResponseFactory {

    private ErrorResponseFactory() {
    }

    public static ResponseEntity<Object> build(int status, String transactionId, String originalMessage) {
        switch (status) {
            case 400:
                return buildResponse(
                        ErrorCatalog.BAD_REQUEST_STATUS_CODE,
                        ErrorCatalog.BAD_REQUEST_STATUS,
                        ErrorCatalog.BAD_REQUEST_MESSAGE,
                        ErrorCatalog.BAD_REQUEST_CODE,
                        ErrorCatalog.BAD_REQUEST_EXCEPTION_MESSAGE,
                        transactionId,
                        400
                );
            case 401:
                return buildResponse(
                        ErrorCatalog.UNAUTHORIZED_STATUS_CODE,
                        ErrorCatalog.UNAUTHORIZED_STATUS,
                        ErrorCatalog.UNAUTHORIZED_MESSAGE,
                        ErrorCatalog.UNAUTHORIZED_CODE,
                        ErrorCatalog.UNAUTHORIZED_EXCEPTION_MESSAGE,
                        transactionId,
                        401
                );
            case 403:
                return buildResponse(
                        ErrorCatalog.FORBIDDEN_STATUS_CODE,
                        ErrorCatalog.FORBIDDEN_STATUS,
                        ErrorCatalog.FORBIDDEN_MESSAGE,
                        ErrorCatalog.FORBIDDEN_CODE,
                        ErrorCatalog.FORBIDDEN_EXCEPTION_MESSAGE,
                        transactionId,
                        403
                );
            case 404:
                return buildResponse(
                        ErrorCatalog.NOT_FOUND_STATUS_CODE,
                        ErrorCatalog.NOT_FOUND_STATUS,
                        ErrorCatalog.NOT_FOUND_MESSAGE,
                        ErrorCatalog.NOT_FOUND_CODE,
                        ErrorCatalog.NOT_FOUND_EXCEPTION_MESSAGE,
                        transactionId,
                        404
                );
            case 408:
                return buildResponse(
                        ErrorCatalog.REQUEST_TIMEOUT_STATUS_CODE,
                        ErrorCatalog.REQUEST_TIMEOUT_STATUS,
                        ErrorCatalog.REQUEST_TIMEOUT_MESSAGE,
                        ErrorCatalog.REQUEST_TIMEOUT_CODE,
                        ErrorCatalog.REQUEST_TIMEOUT_EXCEPTION_MESSAGE,
                        transactionId,
                        408
                );
            case 409:
                return buildResponse(
                        ErrorCatalog.CONFLICT_STATUS_CODE,
                        ErrorCatalog.CONFLICT_STATUS,
                        ErrorCatalog.CONFLICT_MESSAGE,
                        ErrorCatalog.CONFLICT_CODE,
                        ErrorCatalog.CONFLICT_EXCEPTION_MESSAGE,
                        transactionId,
                        409
                );
            case 500:
                return buildResponse(
                        ErrorCatalog.INTERNAL_ERROR_STATUS_CODE,
                        ErrorCatalog.INTERNAL_ERROR_STATUS,
                        ErrorCatalog.INTERNAL_ERROR_MESSAGE,
                        ErrorCatalog.INTERNAL_ERROR_CODE,
                        ErrorCatalog.INTERNAL_ERROR_EXCEPTION_MESSAGE,
                        transactionId,
                        500
                );
            case 504:
                return buildResponse(
                        ErrorCatalog.GATEWAY_TIMEOUT_STATUS_CODE,
                        ErrorCatalog.GATEWAY_TIMEOUT_STATUS,
                        ErrorCatalog.GATEWAY_TIMEOUT_MESSAGE,
                        ErrorCatalog.GATEWAY_TIMEOUT_CODE,
                        ErrorCatalog.GATEWAY_TIMEOUT_EXCEPTION_MESSAGE,
                        transactionId,
                        504
                );
            default:
                return buildResponse(
                        String.valueOf(status),
                        "ERROR",
                        "Unhandled error",
                        status + " UNKNOWN_ERROR",
                        originalMessage,
                        transactionId,
                        status
                );
        }
    }

    private static ResponseEntity<Object> buildResponse(
            String statusCode,
            String status,
            String message,
            String code,
            String exceptionMessage,
            String transactionId,
            int httpStatus
    ) {
        ErrorResponse.ErrorDetails errorDetails = ErrorResponse.ErrorDetails.builder()
                .code(code)
                .fields(Map.of(
                        "exceptionType", ErrorCatalog.EXCEPTION_TYPE,
                        "exceptionMessage", exceptionMessage
                ))
                .build();

        ErrorResponse.ErrorData errorData = ErrorResponse.ErrorData.builder()
                .errorDetails(errorDetails)
                .build();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(statusCode)
                .status(status)
                .message(message)
                .data(errorData)
                .timestamp(OffsetDateTime.now().toString())
                .transactionId(transactionId)
                .build();

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
