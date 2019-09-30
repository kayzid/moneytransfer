package com.revolut.money.transfer.exceptions;

import io.micronaut.http.HttpStatus;

/**
 * 
 * The Exception class to handle the different exceptions in the service.
 * 
 * @author Keyur Chitnis
 *
 */
public class MoneyTransferException extends RuntimeException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 4653585892450842443L;
	
	
	private HttpStatus httpStatus;
	
	public MoneyTransferException(Exception ex,HttpStatus httpStatus) {
		super(ex);
		this.httpStatus=httpStatus;
		
	}
	
	public MoneyTransferException(Throwable th,HttpStatus httpStatus) {
		super(th);
		this.httpStatus=httpStatus;
	}
	
	
	public MoneyTransferException(String message,HttpStatus httpStatus) {
		super(message);
		this.httpStatus=httpStatus;
	}

	public MoneyTransferException(String message,int httpStatusCode) {
		super(message);
		this.httpStatus=HttpStatus.valueOf(httpStatusCode);
	}

	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	

}
