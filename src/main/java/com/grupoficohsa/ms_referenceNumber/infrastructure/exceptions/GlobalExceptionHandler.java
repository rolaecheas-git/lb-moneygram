package com.grupoficohsa.ms_referenceNumber.infrastructure.exceptions;
import com.grupoficohsa.crosscutting.utils.ErrorResponseFactory;
import com.grupoficohsa.webclient.models.WebClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(WebClientException.class)
	public ResponseEntity<Object> handleWebClientException(WebClientException ex, WebRequest request) {
	    int status = ex.getStatusCode();
	    String transactionId = request.getHeader("idTransaction");
	    return ErrorResponseFactory.build(status, transactionId, ex.getMessage());
	}

}