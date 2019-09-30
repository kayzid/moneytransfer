package com.revolut.money.transfer.service;


/**
 * The ErrorType enum which maps the error codes to error messages and http status code.
 * 
 * @author Keyur Chitnis
 *
 */
public enum ErrorType {
	EA404("Account does not exist.",404),
	EA400("Account cannot be created. Invalid entry passed",400),
	EA500("Something went wrong. Please retry.", 500),
	ET403("Transaction cannot be completed. Not enough balance.",403),
	EA409("Operation not completed due to conflict.",409),
	EA405("Method not currently spported.",405);
	
	private String errorDescription;
	private int httpStatusCode;
	
	private ErrorType(String errorDescription,int httpStatusCode) {
		this.errorDescription=errorDescription;
		this.httpStatusCode=httpStatusCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}


	/**
	 * Method to find the error type given an http status code.
	 * 
	 * @param httpStatusCode the httpstatus code to look up the error type.
	 * @return ErrorType
	 */
	public static ErrorType findByHttpStatusCode(int httpStatusCode) {
		for(ErrorType et:ErrorType.values()) {
			if(et.getHttpStatusCode() == httpStatusCode)
				return et;
		}
		
		throw new IllegalArgumentException("Invalid http status code passed.");
	}
	
	
}
