package com.revolut.money.transfer.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.model.CreateTransferRequest;

/**
 * Class to build the params required
 * for completing a transfer operation.
 * 
 * @author Keyur Chitnis
 *
 */

@Singleton
public class CreateTransferRequestBuilder implements RequestBuilder<CreateTransferRequest> {

	/**
	 * Method to build the request params required
	 *  by the <code> {@link CreateTransferOperationDBExecutor} <code>
	 * 
	 * 
	 * @param request the create transfer request object
	 * @return Map<String,String> the hashmap of parameters required for completing the operation.
	 */
	@Override
	public Map<String, String> buildRequestForOperation(CreateTransferRequest request) {
		final Map<String,String> requestParamsKvP=new HashMap<>();
		requestParamsKvP.put(MoneyTransferConstants.ORIGINATION_ACCOUNT_ID,request.getOriginAccountId());
		requestParamsKvP.put(MoneyTransferConstants.DESTINATION_ACCOUNT_ID,request.getDestinationAccountId());
		requestParamsKvP.put(MoneyTransferConstants.AMOUNT,Double.toString(request.getAmount()));
		if(request.isSelfTransfer()) {
			requestParamsKvP.put(MoneyTransferConstants.TRANSFER_TYPE,request.getTransactionType().name());
			requestParamsKvP.put(MoneyTransferConstants.SELF_TRANSFER, Boolean.toString(request.isSelfTransfer()));
		}
		
		
		return requestParamsKvP;
	}
	
	

}
