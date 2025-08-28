package com.grupoficohsa.crosscutting.constants;

public class ErrorCatalog {
    public static final String EXCEPTION_TYPE = "ApiException";

    // --- 400 BAD REQUEST ---
    public static final String BAD_REQUEST_STATUS_CODE = "400";
    public static final String BAD_REQUEST_STATUS = "ERROR";
    public static final String BAD_REQUEST_CODE = "400 BAD_REQUEST";
    public static final String BAD_REQUEST_MESSAGE = "Bad Request - Invalid request data or missing required parameters.";
    public static final String BAD_REQUEST_EXCEPTION_MESSAGE = "Invalid request data or missing required parameters";

    // --- 401 UNAUTHORIZED ---
    public static final String UNAUTHORIZED_STATUS_CODE = "401";
    public static final String UNAUTHORIZED_STATUS = "ERROR";
    public static final String UNAUTHORIZED_CODE = "401 UNAUTHORIZED";
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized - Missing or invalid credentials, or expired token.";
    public static final String UNAUTHORIZED_EXCEPTION_MESSAGE = "No autorizado. Token de acceso inválido o ausente.";

    // --- 403 FORBIDDEN ---
    public static final String FORBIDDEN_STATUS_CODE = "403";
    public static final String FORBIDDEN_STATUS = "ERROR";
    public static final String FORBIDDEN_CODE = "403 FORBIDDEN";
    public static final String FORBIDDEN_MESSAGE ="Forbidden - Insufficient permissions for authenticity evaluation.";
    public static final String FORBIDDEN_EXCEPTION_MESSAGE ="Acceso denegado. No tienes los permisos necesarios.";

    // --- 404 NOT FOUND ---
    public static final String NOT_FOUND_STATUS_CODE = "404";
    public static final String NOT_FOUND_STATUS = "ERROR";
    public static final String NOT_FOUND_CODE = "404 NOT_FOUND";
    public static final String NOT_FOUND_MESSAGE ="Not Found - No data found for the data provided.";
    public static final String NOT_FOUND_EXCEPTION_MESSAGE = "El depósito a plazo fijo no fue encontrado.";

    // --- 408 REQUEST TIMEOUT ---
    public static final String REQUEST_TIMEOUT_STATUS_CODE = "408";
    public static final String REQUEST_TIMEOUT_STATUS = "ERROR";
    public static final String REQUEST_TIMEOUT_CODE = "408 REQUEST_TIMEOUT";
    public static final String REQUEST_TIMEOUT_MESSAGE ="Request Timeout - The request exceeded the configured timeout.";
    public static final String REQUEST_TIMEOUT_EXCEPTION_MESSAGE ="Tiempo de espera agotado. Intenta nuevamente.";

    // --- 409 CONFLICT ---
    public static final String CONFLICT_STATUS_CODE = "409";
    public static final String CONFLICT_STATUS = "ERROR";
    public static final String CONFLICT_CODE = "409 CONFLICT";
    public static final String CONFLICT_MESSAGE ="Conflict - Request conflicts with the current resource state (e.g., duplicate creation).";
    public static final String CONFLICT_EXCEPTION_MESSAGE ="Conflicto detectado en el procesamiento de la solicitud.";

    // --- 500 INTERNAL SERVER ERROR ---
    public static final String INTERNAL_ERROR_STATUS_CODE = "500";
    public static final String INTERNAL_ERROR_STATUS = "ERROR";
    public static final String INTERNAL_ERROR_CODE = "500 INTERNAL_SERVER_ERROR";
    public static final String INTERNAL_ERROR_MESSAGE ="Internal Server Error - Unexpected server error preventing request completion.";
    public static final String INTERNAL_ERROR_EXCEPTION_MESSAGE ="Error interno del servidor. Intenta más tarde.";

    // --- 504 GATEWAY TIMEOUT ---
    public static final String GATEWAY_TIMEOUT_STATUS_CODE = "504";
    public static final String GATEWAY_TIMEOUT_STATUS = "ERROR";
    public static final String GATEWAY_TIMEOUT_CODE = "504 GATEWAY_TIMEOUT";
    public static final String GATEWAY_TIMEOUT_MESSAGE = "Gateway Timeout - Gateway timed out at the limit set in APIM.";
    public static final String GATEWAY_TIMEOUT_EXCEPTION_MESSAGE ="Tiempo de espera agotado en la pasarela de red.";
}
