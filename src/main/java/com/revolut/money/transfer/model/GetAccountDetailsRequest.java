package com.revolut.money.transfer.model;

import com.revolut.money.transfer.service.Request;

/**
 * The Account Details Request class which contains the Accout ID.
 * 
 * @author Keyur Chitnis
 *
 */
public class GetAccountDetailsRequest implements Request {
	
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	

}
