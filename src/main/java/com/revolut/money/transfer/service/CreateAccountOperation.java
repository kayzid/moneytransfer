package com.revolut.money.transfer.service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.revolut.money.transfer.model.AccountDetailsResponse;
import com.revolut.money.transfer.model.CreateAccountRequest;

/**
 * The concrete operation class which extends
 * <code> {@link DBOperation} </code> abstract class. This class acts as wrapper for the <code> {@link CreateAccountsOperationDBExecutor}
 * </code> and <code> {@link CreateAccountRequestBuilder} </code> class. 
 * This class is used to set the RequestBuilder and the DBOperationExecutor in the
 * super class. 
 * 
 * 
 * @author Keyur Chitnis
 *
 */
@Singleton
public class CreateAccountOperation extends DBOperation<CreateAccountRequest, AccountDetailsResponse, CreateAccountsOperationDBExecutor, CreateAccountRequestBuilder> {

	@Inject 
	@Named("createAccountOperation")
	private CreateAccountsOperationDBExecutor createAccountsOperationDBExectuor;
	
	@Inject
	private CreateAccountRequestBuilder createAccountRequestBuilder;
	
	public CreateAccountOperation(CreateAccountsOperationDBExecutor createAccountsOperationDBExecutor,CreateAccountRequestBuilder createAccountRequestBuilder) {
		this.createAccountsOperationDBExectuor=createAccountsOperationDBExecutor;
		this.createAccountRequestBuilder=createAccountRequestBuilder;
		super.setOperationExecutor(createAccountsOperationDBExecutor);
		super.setRequestBuilder(createAccountRequestBuilder);
	}
	
	
}
