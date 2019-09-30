package com.revolut.money.transfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.service.Request;

/**
 * The Transfer request class which handles the money transfer operation.
 * 
 * 
 * @author Keyur Chitnis
 *
 */
public class CreateTransferRequest implements Request {

	@JsonProperty(MoneyTransferConstants.ORIGINATION_ACCOUNT_ID)
	private String originAccountId;
	
	@JsonProperty(MoneyTransferConstants.DESTINATION_ACCOUNT_ID)
	private String destinationAccountId;
	
	@JsonProperty(MoneyTransferConstants.AMOUNT)
	private double amount;
	
	@JsonProperty(MoneyTransferConstants.CURRENCY)
	private CurrencyType currencyType;
	
	@JsonProperty(MoneyTransferConstants.TRANSFER_TYPE)
	private TransactionType transactionType;
	
	@JsonProperty(MoneyTransferConstants.SELF_TRANSFER)
	private boolean selfTransfer;


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public CurrencyType getCurrencyType() {
		return currencyType;
	}


	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}


	public enum CurrencyType{
		USD,
		EUR,
		GBP;
	}


	public String getOriginAccountId() {
		return originAccountId;
	}


	public void setOriginAccountId(String originAccountId) {
		this.originAccountId = originAccountId;
	}


	public String getDestinationAccountId() {
		return destinationAccountId;
	}


	public void setDestinationAccountId(String destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}


	public TransactionType getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}


	public boolean isSelfTransfer() {
		return selfTransfer;
	}


	public void setSelfTransfer(boolean selfTransfer) {
		this.selfTransfer = selfTransfer;
	}
}

