package com.revolut.money.transfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.service.Request;

/**
 * The Update Account Request class to support 
 * update account operation.
 * 
 * @author Keyur Chitnis
 *
 */
public class UpdateAccountRequest implements Request {
	
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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getLoginIPAddress() {
		return loginIPAddress;
	}

	public void setLoginIPAddress(String loginIPAddress) {
		this.loginIPAddress = loginIPAddress;
	}


}
