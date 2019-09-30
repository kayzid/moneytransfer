package com.revolut.money.transfer.exceptions;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.micronaut.http.HttpStatus;

public class MoneyTransferExceptionTest {

	MoneyTransferException exception;
	private static final String TEST="Test";


	@Test
	public void testWithException() {
		exception=new MoneyTransferException(new RuntimeException(TEST), HttpStatus.BAD_REQUEST);
		assertNotNull(exception);
		assertEquals(TEST,exception.getCause().getMessage());
		assertEquals(HttpStatus.BAD_REQUEST,exception.getHttpStatus());
	}
	
	@Test
	public void testWithThrowable() {
		exception=new MoneyTransferException(new Throwable(TEST), HttpStatus.BAD_REQUEST);
		assertNotNull(exception);
		assertEquals(TEST,exception.getCause().getMessage());
		assertEquals(HttpStatus.BAD_REQUEST,exception.getHttpStatus());
	}
	
	@Test
	public void testWithMessage() {
		exception=new MoneyTransferException(TEST, HttpStatus.BAD_REQUEST);
		assertNotNull(exception);
		assertEquals(TEST,exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST,exception.getHttpStatus());
	}
	
	@Test
	public void testWithMesageAndStatusCode() {
		exception=new MoneyTransferException(TEST, HttpStatus.BAD_REQUEST.getCode());
		assertNotNull(exception);
		assertEquals(TEST,exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST,exception.getHttpStatus());
	}
	
	

}
