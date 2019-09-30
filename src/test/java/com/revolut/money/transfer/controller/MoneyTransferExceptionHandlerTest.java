package com.revolut.money.transfer.controller;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.money.transfer.exceptions.MoneyTransferException;

import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.simple.SimpleHttpRequest;

public class MoneyTransferExceptionHandlerTest {
	
	public MoneyTransferExceptionHandler handler;

	@Before
	public void before() throws Exception {
		handler=new MoneyTransferExceptionHandler(new ObjectMapper());
	}

	
	@Test
	public void handleTestForBadRequest() {
		final SimpleHttpRequest request=new SimpleHttpRequest<String>(HttpMethod.POST, "v1/accounts/", "");
		final MoneyTransferException exception=new MoneyTransferException("test",HttpStatus.BAD_REQUEST);
		MutableHttpResponse<Object>  response=handler.handle(request , exception);
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatus());
	}
	
	@Test
	public void handleTestForNotFound() {
		final SimpleHttpRequest request=new SimpleHttpRequest<String>(HttpMethod.POST, "v1/accounts/", "");
		final MoneyTransferException exception=new MoneyTransferException("test",HttpStatus.NOT_FOUND);
		MutableHttpResponse<Object>  response=handler.handle(request , exception);
		assertEquals(HttpStatus.NOT_FOUND,response.getStatus());
	}
	
	@Test
	public void handleTestForForbidden() {
		final SimpleHttpRequest request=new SimpleHttpRequest<String>(HttpMethod.POST, "v1/accounts/", "");
		final MoneyTransferException exception=new MoneyTransferException("test",HttpStatus.FORBIDDEN);
		MutableHttpResponse<Object>  response=handler.handle(request , exception);
		assertEquals(HttpStatus.FORBIDDEN,response.getStatus());
	}
	
	@Test
	public void handleTestForMethodNotAllowed() {
		final SimpleHttpRequest request=new SimpleHttpRequest<String>(HttpMethod.POST, "v1/accounts/", "");
		final MoneyTransferException exception=new MoneyTransferException("test",HttpStatus.METHOD_NOT_ALLOWED);
		MutableHttpResponse<Object>  response=handler.handle(request , exception);
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED,response.getStatus());
	}
	
	@Test
	public void handleTestForMethodConflict() {
		final SimpleHttpRequest request=new SimpleHttpRequest<String>(HttpMethod.POST, "v1/accounts/", "");
		final MoneyTransferException exception=new MoneyTransferException("test",HttpStatus.CONFLICT);
		MutableHttpResponse<Object>  response=handler.handle(request , exception);
		assertEquals(HttpStatus.CONFLICT,response.getStatus());
	}
	
	@Test
	public void handleServerError() {
		final SimpleHttpRequest request=new SimpleHttpRequest<String>(HttpMethod.POST, "v1/accounts/", "");
		final MoneyTransferException exception=new MoneyTransferException("test",HttpStatus.INTERNAL_SERVER_ERROR);
		MutableHttpResponse<Object>  response=handler.handle(request , exception);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatus());
	}

}
