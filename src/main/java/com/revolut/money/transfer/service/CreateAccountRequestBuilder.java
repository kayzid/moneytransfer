package com.revolut.money.transfer.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.model.CreateAccountRequest;

/**
 * Class to build the params required
 * for creating an account.
 * 
 * @author Keyur Chitnis
 *
 */

@Singleton
public class CreateAccountRequestBuilder implements RequestBuilder<CreateAccountRequest> {

	/**
	 * Method to build the request params required
	 *  by the <code> {@link CreateAccountsOperationDBExecutor} <code>
	 * 
	 * 
	 * @param request the create transfer request object
	 * @return Map<String,String> the hashmap of parameters required for completing the operation.
	 */
	@Override
	public Map<String, String> buildRequestForOperation(CreateAccountRequest createAccountRequest) {
		final Map<String,String> requestParamsKvP=new HashMap<>();
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_HOLDER_FIRST_NAME, createAccountRequest.getAccountDetails().getAccountHolderFirstName());
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_HOLDER_LAST_NAME, createAccountRequest.getAccountDetails().getAccountHolderLastName());
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_HOLDER_BALANCE, Double.toString(createAccountRequest.getAccountDetails().getAccountBalance()));
		requestParamsKvP.put(MoneyTransferConstants.LAST_LOGIN_IP, createAccountRequest.getLoginIPAddress());
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_HOLDER_COUNTRY, createAccountRequest.getAccountDetails().getAccountHolderCountry());
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_TYPE, createAccountRequest.getAccountType().name());
		
		
		return requestParamsKvP;
	}

	
}
