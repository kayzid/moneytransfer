package com.revolut.money.transfer.service;

import java.util.Map;
import java.util.Optional;

/**
 * 
 * The base interface for performing the db operation. 
 * 
 * @author Keyur Chitnis
 *
 * @param <R> the response
 */

public interface DBOperationExecutor<R extends Response> {

	/**
	 * Method to perform db operation. 
	 * 
	 * @param requestParams the map of request params.
	 * @return Optional<R> the optional response
	 * @throws Exception incase of any runtime exceptions.
	 */
	public Optional<R> performDBOperation(Map<String,String> requestParams) throws Exception;
}
