package com.revolut.money.transfer.service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.revolut.money.transfer.model.AccountDetailsResponse;
import com.revolut.money.transfer.model.GetAccountDetailsRequest;

/**
 * The concrete operation class which extends
 * <code> {@link DBOperation} </code> abstract class. This class acts as wrapper for the <code> {@link CreateAccountsOperationDBExecutor}
 * </code> and <code> {@link GetAccountDetailsRequestBuilder} </code> class. 
 * This class is used to set the RequestBuilder and the DBOperationExecutor in the
 * super class.  
 * 
 * @author Keyur Chitnis
 *
 */
@Singleton
@Named("getAccountDetailsOperation")
public class GetAccountDetailsOperation extends DBOperation<GetAccountDetailsRequest, AccountDetailsResponse, GetAccountDetailsOperationDBExecutor, GetAccountDetailsRequestBuilder> {

	@Inject
	private GetAccountDetailsOperationDBExecutor getAccountDetailsOperationDBExecutor;
	
	@Inject
	private GetAccountDetailsRequestBuilder getAccountDetailsRequestBuilder;
	
	public GetAccountDetailsOperation(GetAccountDetailsOperationDBExecutor getAccountDetailsOperationDBExecutor,GetAccountDetailsRequestBuilder getAccountDetailsRequestBuilder) {
		super.setOperationExecutor(getAccountDetailsOperationDBExecutor);
		super.setRequestBuilder(getAccountDetailsRequestBuilder);
	}
}
