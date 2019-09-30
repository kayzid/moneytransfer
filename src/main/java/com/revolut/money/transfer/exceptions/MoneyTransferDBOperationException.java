package com.revolut.money.transfer.exceptions;

/**
 * The Exception class to handle the exceptions raised by different db operations.
 * 
 * 
 * @author Keyur Chitnis
 *
 */
public class MoneyTransferDBOperationException extends Exception {

	/**
	 * Generated Serial Version ID
	 * 
	 */
	private static final long serialVersionUID = 333802629701351568L;
	
	public MoneyTransferDBOperationException(Exception ex) {
		super(ex);
	}
	
	public MoneyTransferDBOperationException(Throwable th) {
		super(th);
	}
	
	public MoneyTransferDBOperationException(String message) {
		super(message);
	}

}
