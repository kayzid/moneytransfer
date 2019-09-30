package com.revolut.money.transfer.service;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.money.transfer.exceptions.MoneyTransferException;
import com.revolut.money.transfer.model.CreateTransferRequest;
import com.revolut.money.transfer.model.ServiceResponse;
import com.revolut.money.transfer.model.TransferResponse;
import io.micronaut.http.HttpStatus;

/**
 * This class supports various transfer operations. Namely transfer between accounts
 * and transfer within an account.
 * 
 * @author Keyur Chitnis
 *
 */

@Singleton
public class TransferService {

	private static final Logger log=LoggerFactory.getLogger(TransferService.class);
	
	@Inject
	private ObjectMapper objectMapper;
	
	@Inject
	@Named("createTransferDBOperation")
	private DBOperation<CreateTransferRequest,TransferResponse,CreateTransferOperationDBExecutor,CreateTransferRequestBuilder> createTransferDBOperation; 
	
	@Inject 
	private ServiceResponseGenerator serviceResponseGenerator;
	
	
	
	public ServiceResponse getTransfersById(String id) {
		//No OP		
		throw new MoneyTransferException("Operation not supported!",HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	/**
	 * Method to execute the transfer operation between accounts or 
	 * credit or debit money in same account.
	 * 
	 * @param createTransferRequest the create transfer request object.
	 * @return ServiceResponse the service response.
	 * @throws MoneyTransferException the money transfer exception incase of runtime exceptions.
	 */
	public ServiceResponse performTransfer(CreateTransferRequest createTransferRequest) {
		try {
			final Optional<TransferResponse> response=createTransferDBOperation.executeOperation(createTransferRequest, OperationType.Database);
			if(!response.isPresent()) {
				log.error("perform-transfer-operation: Something went wrong in completing the request");
				throw new MoneyTransferException(ErrorType.EA500.getErrorDescription(),ErrorType.EA500.getHttpStatusCode());
			}
			return serviceResponseGenerator.generateServiceResponse(objectMapper.convertValue(response.get(),JsonNode.class), HttpStatus.OK.getCode());
		}
		catch(MoneyTransferException ex) {
			throw ex;
		}
		catch(Exception ex) {
			log.error("perform-transfer-operation: Something went wrong: ",ex);
			throw new MoneyTransferException(ErrorType.EA500.getErrorDescription(),ErrorType.EA500.getHttpStatusCode());
		}
		
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setCreateTransferDBOperation(
			DBOperation<CreateTransferRequest, TransferResponse, CreateTransferOperationDBExecutor, CreateTransferRequestBuilder> createTransferDBOperation) {
		this.createTransferDBOperation = createTransferDBOperation;
	}

	public void setServiceResponseGenerator(ServiceResponseGenerator serviceResponseGenerator) {
		this.serviceResponseGenerator = serviceResponseGenerator;
	}
	
}
