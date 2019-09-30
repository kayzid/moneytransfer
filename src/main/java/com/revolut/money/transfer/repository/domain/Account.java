package com.revolut.money.transfer.repository.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNT")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	
	/**
	 * Generated Serial VersionID
	 */
	private static final long serialVersionUID = 5239641009905613481L;
	
	
	@Id
	@Column(name="ACCOUNT_ID")
	private String accountId;
	
	@Column(name="ACCOUNT_HOLDER_FIRST_NAME")
	private String accountHolderFirstName;
	
	@Column(name="ACCOUNT_HOLDER_LAST_NAME")
	private String accountHolderLastName;
	
	@Column(name="ACCOUNT_HOLDER_COUNTRY")
	private String accountHolderCountry;
	
	@Column(name="ACCOUNT_BALANCE",precision=10, scale=2)
	private double accountBalance;
	
	@Column(name="ACCOUNT_LAST_LOGIN_DT")
	private long accountLastLoginDate;
	
	@Column(name="ACCOUNT_TYPE")
	private String accountType;

	@Column(name="LAST_LOGIN_IP_ADDRESS")
	private String accountLastLoginIPAddress;

	@Column(name="CREATE_DT")
	private long createdDate;
	
	@Column(name="UPDATED_DT")
	private long updatedDate;
	
	public long getAccountLastLoginDate() {
		return accountLastLoginDate;
	}

	public void setAccountLastLoginDate(long accountLastLoginDate) {
		this.accountLastLoginDate = accountLastLoginDate;
	}

	public String getAccountLastLoginIPAddress() {
		return accountLastLoginIPAddress;
	}

	public void setAccountLastLoginIPAddress(String accountLastLoginIPAddress) {
		this.accountLastLoginIPAddress = accountLastLoginIPAddress;
	}

	public long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(long updatedDate) {
		this.updatedDate = updatedDate;
	}

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

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getAccountHolderCountry() {
		return accountHolderCountry;
	}

	public void setAccountHolderCountry(String accountHolderCountry) {
		this.accountHolderCountry = accountHolderCountry;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	
	

}
