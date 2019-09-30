package com.revolut.money.transfer.model;


import com.revolut.money.transfer.repository.domain.Account;
import com.revolut.money.transfer.service.Response;

/**
 * The response class for fetch account by id or when an account is created.
 * This class is a direct copy of the {@link Account} entity class.
 * 
 * @author Keyur Chitnis
 *
 */
public class AccountDetailsResponse implements Response {
	
	
	private String accountId;
	
	
	private String accountHolderFirstName;
	
	
	private String accountHolderLastName;
	
	
	private String accountHolderCountry;
	
	
	private double accountBalance;
	
	
	private long accountLastLoginDate;
	
	
	private String accountType;

	
	private String accountLastLoginIPAddress;

	
	private long createdDate;
	
	
	private long updatedDate;


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
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


	public double getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}


	public long getAccountLastLoginDate() {
		return accountLastLoginDate;
	}


	public void setAccountLastLoginDate(long accountLastLoginDate) {
		this.accountLastLoginDate = accountLastLoginDate;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public String getAccountLastLoginIPAddress() {
		return accountLastLoginIPAddress;
	}


	public void setAccountLastLoginIPAddress(String accountLastLoginIPAddress) {
		this.accountLastLoginIPAddress = accountLastLoginIPAddress;
	}


	public long getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}


	public long getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(long updatedDate) {
		this.updatedDate = updatedDate;
	}

}
