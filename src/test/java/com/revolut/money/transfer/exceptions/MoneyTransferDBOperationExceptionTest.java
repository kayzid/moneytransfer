package com.revolut.money.transfer.exceptions;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MoneyTransferDBOperationExceptionTest {
	MoneyTransferDBOperationException exception;

	private static final String TEST="Test";
	
	@Test
	public void testException() {
		exception=new MoneyTransferDBOperationException(new RuntimeException(TEST));
		assertNotNull(exception);
		assertEquals(TEST, exception.getCause().getMessage());
	}
	
	@Test
	public void testExceptionWithThrowable() {
		exception=new MoneyTransferDBOperationException(new Throwable(TEST));
		assertNotNull(exception);
		assertEquals(TEST, exception.getCause().getMessage());
	}
	
	@Test
	public void testExceptionWithMessage() {
		exception=new MoneyTransferDBOperationException(TEST);
		assertNotNull(exception);
		assertEquals(TEST, exception.getMessage());
	}

}
