package com.revolut.money.transfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revolut.money.transfer.MoneyTransferConstants;

/**
 * The Account Details which contains account detail information.
 * @author Keyur Chitnis
 *
 */
public class AccountDetails {

	@JsonProperty(MoneyTransferConstants.ACCOUNT_HOLDER_FIRST_NAME)
	private String accountHolderFirstName;
	
	@JsonProperty(MoneyTransferConstants.ACCOUNT_HOLDER_LAST_NAME)
	private String accountHolderLastName;
	
	@JsonProperty(MoneyTransferConstants.ACCOUNT_HOLDER_COUNTRY)
	private String accountHolderCountry;
	
	@JsonProperty(MoneyTransferConstants.ACCOUNT_BALANCE)
	private double accountBalance;
	
	
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountHolderFirstName() {
		return accountHolderFirstName;
	}
	public void setAccountHolderFirstName(String accountHolderFirstName) {
		this.accountHolderFirstName = accountHolderFirstName;
	}
	public String getAccountHolderLastName() {
		return accountHolderLastName;
	}
	public void setAccountHolderLastName(String accountHolderLastName) {
		this.accountHolderLastName = accountHolderLastName;
	}
	public String getAccountHolderCountry() {
		return accountHolderCountry;
	}
	public void setAccountHolderCountry(String accountHolderCountry) {
		this.accountHolderCountry = accountHolderCountry;
	}
	
	
	
	
	
	
}
