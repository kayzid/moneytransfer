package com.revolut.money.transfer.service;

import java.util.Map;

/**
 * 
 * The base interface for RequestBuilder.
 * @author Keyur Chitnis
 *
 * @param <R> the request class
 */
public interface RequestBuilder<R extends Request> {
	
	public Map<String,String> buildRequestForOperation(R request);

}
