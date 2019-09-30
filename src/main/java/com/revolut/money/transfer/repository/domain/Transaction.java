//package com.revolut.money.transfer.repository.domain;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="TRANSACTION")
//@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
//public class Transaction implements Serializable {
//
//	
//	/**
//	 * Generated Serial Version ID.
//	 */
//	private static final long serialVersionUID = 4509975202798445820L;
//
//	@Id
//	@GeneratedValue(generator="system-uuid")
//	@Column(name="TRANSACTION_ID")
//	private String transactionId;
//	
//	@ManyToOne
//	@JoinColumn(name="SOURCE_ACCOUNT_ID",referencedColumnName="ACCOUNT_ID")
//	private Account account;
//	
//	
//	@Column(name="TRANSACTION_AMOUNT",precision=10,scale=2)
//	private double transactionAmount;
//	
//	@Column(name="TRANSACTION_START_DT")
//	private long transactionStartDate;
//	
//	@Column(name="TRANSACTION_COMPLETE_DT")
//	private long transactionCompleteDate;
//	
//	@Column(name="CURRENCY")
//	private String currency;
//
//	public String getTransactionId() {
//		return transactionId;
//	}
//
//	public void setTransactionId(String transactionId) {
//		this.transactionId = transactionId;
//	}
//
//	public Account getAccount() {
//		return account;
//	}
//
//	public void setAccount(Account account) {
//		this.account = account;
//	}
//
//	public double getTransactionAmount() {
//		return transactionAmount;
//	}
//
//	public void setTransactionAmount(double transactionAmount) {
//		this.transactionAmount = transactionAmount;
//	}
//
//	public long getTransactionStartDate() {
//		return transactionStartDate;
//	}
//
//	public void setTransactionStartDate(long transactionStartDate) {
//		this.transactionStartDate = transactionStartDate;
//	}
//
//	public long getTransactionCompleteDate() {
//		return transactionCompleteDate;
//	}
//
//	public void setTransactionCompleteDate(long transactionCompleteDate) {
//		this.transactionCompleteDate = transactionCompleteDate;
//	}
//
//	public String getCurrency() {
//		return currency;
//	}
//
//	public void setCurrency(String currency) {
//		this.currency = currency;
//	}
//
//
//}
