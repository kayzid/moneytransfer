package com.revolut.money.transfer.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.model.GetAccountDetailsRequest;

@Singleton
public class GetAccountDetailsRequestBuilder implements RequestBuilder<GetAccountDetailsRequest> {

	@Override
	public Map<String, String> buildRequestForOperation(GetAccountDetailsRequest request) {
		final Map<String,String> requestParamsKvP=new HashMap<>();
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_ID, request.getAccountId());
		return requestParamsKvP;
	}

}
