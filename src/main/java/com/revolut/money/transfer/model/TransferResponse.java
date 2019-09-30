package com.revolut.money.transfer.model;

import com.revolut.money.transfer.service.Response;

/**
 * The response class to confirn and send back information about
 * a successful completion of the money transfer.
 * @author Keyur Chitnis
 *
 */
public class TransferResponse implements Response {
	
	private String message;
	
	public TransferResponse(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
