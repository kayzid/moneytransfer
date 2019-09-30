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
import com.revolut.money.transfer.model.AccountDetailsResponse;
import com.revolut.money.transfer.model.CreateAccountRequest;
import com.revolut.money.transfer.model.GetAccountDetailsRequest;
import com.revolut.money.transfer.model.ServiceResponse;
import com.revolut.money.transfer.model.UpdateAccountRequest;
import io.micronaut.http.HttpStatus;

/**
 * The Account Service class to handle the create account, get account operation.
 * 
 * 
 * @author Keyur Chitnis
 *
 */

@Singleton
public class AccountsService {
	
	private static final Logger log=LoggerFactory.getLogger(AccountsService.class);
	
	@Inject
	private ObjectMapper objectMapper;
	
	@Inject
	@Named("createAccountOperation")
	private DBOperation<CreateAccountRequest, AccountDetailsResponse, CreateAccountsOperationDBExecutor, CreateAccountRequestBuilder> createAccountOperation;
	
	@Inject
	@Named("getAccountDetailsOperation")
	private DBOperation<GetAccountDetailsRequest, AccountDetailsResponse, GetAccountDetailsOperationDBExecutor, GetAccountDetailsRequestBuilder> getAccountDetailsOperation;
	
	@Inject 
	private ServiceResponseGenerator serviceResponseGenerator;
	
	/**
	 * The method to create an account by calling the create account operation class.
	 * 
	 * @param createAccountRequest the create account request.
	 * @return ServiceResponse the serviceResponse with http status code.
	 * @throws MoneyTransferException the runtim exception incase of any issues.
	 */
	public ServiceResponse createAccount(CreateAccountRequest createAccountRequest) {
		
		try {
			Optional<AccountDetailsResponse> response=createAccountOperation.executeOperation(createAccountRequest, OperationType.Database);
			return serviceResponseGenerator.generateServiceResponse(objectMapper.convertValue(response, JsonNode.class),201);
		}
		catch(MoneyTransferException ex) {
			throw ex;
		}
		catch(Exception ex) {
			log.error("create-account: Error creating account {}",ex);
			throw new MoneyTransferException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	/**
	 * Method to lookup an account by account id.
	 * 
	 * @param accountId the account id to look up.
	 * @return ServiceResponse the service response along with http code.
	 * @throws MoneyTransferException the runtim exception incase of any issues.
	 */
	public ServiceResponse findAccountsById(String accountId) {
		try {
			final GetAccountDetailsRequest accountDetailsRequest=new GetAccountDetailsRequest();
			accountDetailsRequest.setAccountId(accountId);
			Optional<AccountDetailsResponse> accountDetails=getAccountDetailsOperation.executeOperation(accountDetailsRequest, OperationType.Database);
			if(accountDetails.isPresent()) {
				return serviceResponseGenerator.generateServiceResponse(objectMapper.convertValue(accountDetails.get(), JsonNode.class),200);		
			}			
			log.error("get-account: Account not found for id {}",accountId);
			throw new MoneyTransferException(ErrorType.EA404.getErrorDescription(),ErrorType.EA404.getHttpStatusCode());
		}
		catch(MoneyTransferException ex) {
			throw ex;
		}
		catch(Exception ex) {
			log.error("create-account: Error creating account {}",ex);
			throw new MoneyTransferException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	/**
	 *  Method to update account details. Currently not supported.
	 *  
	 * @param updateAccountRequest
	 * @param accountId
	 * @return ServiceResponse serviceReponse with http code.
	 */
	public ServiceResponse updateAccount(UpdateAccountRequest updateAccountRequest,String accountId) {
		// No Op
		throw new MoneyTransferException("Operation not supported!",HttpStatus.METHOD_NOT_ALLOWED);
		
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setCreateAccountOperation(
			DBOperation<CreateAccountRequest, AccountDetailsResponse, CreateAccountsOperationDBExecutor, CreateAccountRequestBuilder> createAccountOperation) {
		this.createAccountOperation = createAccountOperation;
	}

	public void setGetAccountDetailsOperation(
			DBOperation<GetAccountDetailsRequest, AccountDetailsResponse, GetAccountDetailsOperationDBExecutor, GetAccountDetailsRequestBuilder> getAccountDetailsOperation) {
		this.getAccountDetailsOperation = getAccountDetailsOperation;
	}

	public void setServiceResponseGenerator(ServiceResponseGenerator serviceResponseGenerator) {
		this.serviceResponseGenerator = serviceResponseGenerator;
	}
	

}
