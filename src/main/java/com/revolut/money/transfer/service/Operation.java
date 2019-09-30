package com.revolut.money.transfer.service;

import java.util.Optional;


/**
 * 
 * The Base interface for executing an operation. This can be extended to perform
 * database operations, webservice operations etc.
 * 
 * @author Keyur Chitnis
 *
 * @param <R> The Request
 * @param <S> The Response
 */

public interface Operation<R extends Request, S extends Response> {
	
	public Optional<S> executeOperation(R request,OperationType operationType) throws Exception;

}
