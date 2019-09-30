package com.revolut.money.transfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.service.Request;

/**
 * The Request class to create an account.
 * 
 * @author Keyur Chitnis
 *
 */
public class CreateAccountRequest implements Request {
	
	@JsonProperty(MoneyTransferConstants.ACCOUNT_DETAILS)
	private AccountDetails accountDetails;
	
	@JsonProperty(MoneyTransferConstants.ACCOUNT_TYPE)
	private AccountType accountType;
	
	@JsonProperty(MoneyTransferConstants.LOGIN_IP_ADDRESS)
	private String loginIPAddress;

	
	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	public String getLoginIPAddress() {
		return loginIPAddress;
	}

	public void setLoginIPAddress(String loginIPAddress) {
		this.loginIPAddress = loginIPAddress;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		
		
		return sb.toString();
	}
	

}
