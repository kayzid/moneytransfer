package com.revolut.money.transfer.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.money.transfer.exceptions.MoneyTransferDBOperationException;
import com.revolut.money.transfer.exceptions.MoneyTransferException;


/**
 * This abstract class service as a way to execute the db operation by taking in a request
 * building the request params in correct format
 * and calling the appropriate DBOperationExecutor to execte the operation on the db.
 * 
 * 
 * @author Keyur Chitnis
 *
 * @param <R> Request class
 * @param <S> Response class
 * @param <O> DBOperationExecutor
 * @param <B> RequestBuilder
 */
public abstract class DBOperation<R extends Request,S extends Response,O extends DBOperationExecutor<S>,B extends RequestBuilder<R>> implements Operation<R,S> {

	private static final Logger log=LoggerFactory.getLogger(DBOperation.class);
	
	private O operationExecutor;
	
	private B requestBuilder;
	
	
	/**
	 * The method to execute the operation by building the request in the correct format
	 * using the request builder and then executing the operation using the operation executor.
	 * 
	 * This method returns and Optional response
	 * 
	 * @param request the request to be processed.
	 * @param op the operation type.
	 * 
	 * @throws MoneyTransferDBOperationException an exception incase of any runtime errors.
	 */
	@Override
	public Optional<S> executeOperation(R request,OperationType op) throws MoneyTransferDBOperationException {
		log.info("db-operation-exeution: {}" + request.toString());
		
		try {
			final Optional<S> optionalResponse=operationExecutor.performDBOperation(requestBuilder.buildRequestForOperation(request));
			return optionalResponse;
		}
		catch(MoneyTransferException ex) {
			throw ex;
		}
		catch(Exception ex) {
			log.error("db-operation-execution: Error in performing the operation - {}" ,ex);
			throw new MoneyTransferDBOperationException(ex);
		}
	}
	
	
	public void setOperationExecutor(O operationExecutor) {
		this.operationExecutor=operationExecutor;
	}
	
	public void setRequestBuilder(B requestBuilder) {
		this.requestBuilder=requestBuilder;
	}
}
