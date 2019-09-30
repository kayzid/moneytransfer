package com.revolut.money.transfer.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.exceptions.MoneyTransferException;
import com.revolut.money.transfer.service.ErrorType;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

/**
 * The exception handler class to handle the exceptions thrown by the application.
 * This handler handles all <code> {@link MoneyTransferException}</code> exceptions raised from the code.
 * This handler will take the exception and generate a http response in json format and set the http status 
 * on the http response. 
 * 
 * 
 * @author Keyur Chitnis
 *
 */

@Produces
@Singleton
public class MoneyTransferExceptionHandler implements ExceptionHandler<MoneyTransferException, MutableHttpResponse<Object>> {

	@Inject
	private ObjectMapper objectMapper;
	
	public MoneyTransferExceptionHandler(ObjectMapper objectMapper) {
		this.objectMapper=objectMapper;
	}

	/**
	 * Method to handle the exception and return a MutalbleHttpResponse with the correct status code.
	 * 
	 * @param request the http request.
	 * @param exception the MoneyTrasferException 
	 */
	@Override
	public MutableHttpResponse<Object> handle(HttpRequest request, MoneyTransferException exception) {

		HttpStatus httpStatus=exception.getHttpStatus();
		switch(httpStatus){
		case BAD_REQUEST:

			return HttpResponse.badRequest(generateErrorResponse(exception, httpStatus));
		case NOT_FOUND:

			return HttpResponse.notFound(generateErrorResponse(exception, httpStatus));

		case FORBIDDEN:			
			return HttpResponse.status(httpStatus).body(generateErrorResponse(exception, httpStatus));

		case CONFLICT:				
			return HttpResponse.status(httpStatus).body(generateErrorResponse(exception, httpStatus));
		case METHOD_NOT_ALLOWED:				
			return HttpResponse.status(httpStatus).body(generateErrorResponse(exception, httpStatus));
			
		default:
			return HttpResponse.serverError(generateErrorResponse(exception, httpStatus));
		}
	}

	/**
	 * Method to build the error response.
	 * 
	 * @param exception the exception
	 * @param httpStatus http status
	 * @return JsonNode which contains the error message and the error code.
	 */
	private JsonNode generateErrorResponse(MoneyTransferException exception, HttpStatus httpStatus) {
		return objectMapper.createObjectNode()
				.put(MoneyTransferConstants.MESSAGE,exception.getMessage())
				.put(MoneyTransferConstants.ERROR_CODE, ErrorType.findByHttpStatusCode(httpStatus.getCode()).name());		
	}

	

}
