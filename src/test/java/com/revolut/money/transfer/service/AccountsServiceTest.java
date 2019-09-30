package com.revolut.money.transfer.service;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.money.transfer.exceptions.MoneyTransferDBOperationException;
import com.revolut.money.transfer.exceptions.MoneyTransferException;
import com.revolut.money.transfer.model.AccountDetails;
import com.revolut.money.transfer.model.AccountDetailsResponse;
import com.revolut.money.transfer.model.AccountType;
import com.revolut.money.transfer.model.CreateAccountRequest;
import com.revolut.money.transfer.model.GetAccountDetailsRequest;
import com.revolut.money.transfer.model.ServiceResponse;

import io.micronaut.http.HttpStatus;

public class AccountsServiceTest {
	
	AccountsService service;
	ObjectMapper mapper=new ObjectMapper();
	private DBOperation<CreateAccountRequest, AccountDetailsResponse, CreateAccountsOperationDBExecutor, CreateAccountRequestBuilder> createAccountOperation;
	private DBOperation<GetAccountDetailsRequest, AccountDetailsResponse, GetAccountDetailsOperationDBExecutor, GetAccountDetailsRequestBuilder> getAccountDetailsOperation;
	ServiceResponseGenerator serviceResponseGenerator;

	@Before
	public void setUp() throws Exception {
		service=new AccountsService();
		createAccountOperation=Mockito.mock(CreateAccountOperation.class);
		getAccountDetailsOperation=Mockito.mock(GetAccountDetailsOperation.class);
		service.setCreateAccountOperation(createAccountOperation);
		service.setObjectMapper(mapper);
		service.setGetAccountDetailsOperation(getAccountDetailsOperation);
		serviceResponseGenerator=new ServiceResponseGenerator();
		service.setServiceResponseGenerator(serviceResponseGenerator);
		
		
		
	}

	
	@Test
	public void createAccountTest() {
		try {
			Mockito.when(createAccountOperation.executeOperation(Mockito.any(), Mockito.any())).thenReturn(Optional.of(generateResponse()));
			ServiceResponse response=service.createAccount(createRequest());
			assertNotNull(response.getResponse());
			assertEquals(201,response.getHttpStatus());
		} catch (MoneyTransferException e) {
			fail(e.getMessage());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
	
	}
	
	@Test
	public void createAccountWithExceptionTest() {
		try {
			Mockito.when(createAccountOperation.executeOperation(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException("test"));
			ServiceResponse response=service.createAccount(createRequest());
			fail();
		} catch (MoneyTransferException e) {
			assertNotNull(e.getMessage());
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,e.getHttpStatus());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
	
	}
	
	@Test
	public void createAccountWithMoneyTransferExceptionExceptionTest() {
		try {
			Mockito.when(createAccountOperation.executeOperation(Mockito.any(), Mockito.any())).thenThrow(new MoneyTransferException(ErrorType.EA404.getErrorDescription(),HttpStatus.NOT_FOUND));
			ServiceResponse response=service.createAccount(createRequest());
			fail();
		} catch (MoneyTransferException e) {
			assertNotNull(e.getMessage());
			assertEquals(HttpStatus.NOT_FOUND,e.getHttpStatus());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void findAccountsByIdTest() {
		try {
			Mockito.when(getAccountDetailsOperation.executeOperation(Mockito.any(), Mockito.any())).thenReturn(Optional.of(generateResponse()));
			ServiceResponse response=service.findAccountsById("Test");
			assertNotNull(response.getResponse());
			assertEquals(200,response.getHttpStatus());
		} catch (MoneyTransferException e) {
			fail(e.getMessage());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
	
	}
	
	@Test
	public void findAccountsByIdWithExceptionTest() {
		try {
			Mockito.when(getAccountDetailsOperation.executeOperation(Mockito.any(), Mockito.any())).thenThrow(new MoneyTransferException(ErrorType.EA404.getErrorDescription(),HttpStatus.NOT_FOUND));
			ServiceResponse response=service.findAccountsById("Test");
			fail();
		}
		catch (MoneyTransferException e) {
			assertNotNull(e.getMessage());
			assertEquals(HttpStatus.NOT_FOUND,e.getHttpStatus());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	
	}
	
	@Test
	public void findAccountsByIdWithRuntimeExceptionTest() {
		try {
			Mockito.when(getAccountDetailsOperation.executeOperation(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException("Test"));
			ServiceResponse response=service.findAccountsById("Test");
			fail();
		}
		catch (MoneyTransferException e) {
			assertNotNull(e.getMessage());
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,e.getHttpStatus());
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	
	}
	

	private CreateAccountRequest createRequest() {
		CreateAccountRequest request=new CreateAccountRequest();
		request.setAccountType(AccountType.Savings);
		request.setLoginIPAddress("192.168.1.1");
		final AccountDetails details=new AccountDetails();
		details.setAccountBalance(100);
		details.setAccountHolderCountry("USA");
		details.setAccountHolderFirstName("Test");
		details.setAccountHolderLastName("test");
		request.setAccountDetails(details);
		return request;
	}
	
	
	private AccountDetailsResponse generateResponse() {
		AccountDetailsResponse response=new AccountDetailsResponse();
		response.setAccountBalance(200);
		response.setAccountHolderCountry("USA");
		response.setAccountHolderFirstName("Test");
		response.setAccountHolderLastName("Test");
		response.setAccountId("Test");
		response.setAccountLastLoginDate(Instant.now().toEpochMilli());
		response.setCreatedDate(Instant.now().toEpochMilli());
		response.setUpdatedDate(Instant.now().toEpochMilli());
		response.setAccountType("Checking");
		response.setAccountLastLoginIPAddress("192.168.1.1");
		return response;
	}

}
