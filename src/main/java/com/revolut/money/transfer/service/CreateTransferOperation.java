package com.revolut.money.transfer.service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.revolut.money.transfer.model.CreateTransferRequest;
import com.revolut.money.transfer.model.TransferResponse;

/**
 * Class to create a transfer operation. This class extends
 * <code> {@link DBOperation} </code> abstract class. This class acts as wrapper for the <code> {@link CreateTransferOperationDBExecutor}
 * </code> and <code> {@link CreateTransferRequestBuilder} </code> class. 
 * This class is used to set the RequestBuilder and the DBOperationExecutor in the
 * super class.
 *  
 * 
 * @author Keyur Chitnis
 *
 */

@Singleton
@Named("createTransferDBOperation")
public class CreateTransferOperation extends DBOperation<CreateTransferRequest,TransferResponse,CreateTransferOperationDBExecutor,CreateTransferRequestBuilder>   {

	
	@Inject
	private CreateTransferOperationDBExecutor createTransferOperationDBExecutor;
	
	@Inject
	private CreateTransferRequestBuilder createTransferRequestBuilder;
	
	public CreateTransferOperation(CreateTransferOperationDBExecutor createTransferOperationDBExecutor,CreateTransferRequestBuilder createTransferRequestBuilder) {
		super.setOperationExecutor(createTransferOperationDBExecutor);
		super.setRequestBuilder(createTransferRequestBuilder);
	}
}
