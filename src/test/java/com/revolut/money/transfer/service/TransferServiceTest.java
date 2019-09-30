package com.revolut.money.transfer.service;

import static org.junit.Assert.*;import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.money.transfer.exceptions.MoneyTransferDBOperationException;
import com.revolut.money.transfer.exceptions.MoneyTransferException;
import com.revolut.money.transfer.model.CreateTransferRequest;
import com.revolut.money.transfer.model.ServiceResponse;
import com.revolut.money.transfer.model.TransferResponse;

import io.micronaut.http.HttpStatus;

public class TransferServiceTest {
	TransferService service;
	ObjectMapper mapper=new ObjectMapper();
	private DBOperation<CreateTransferRequest,TransferResponse,CreateTransferOperationDBExecutor,CreateTransferRequestBuilder> createTransferDBOperation;
	ServiceResponseGenerator serviceResponseGenerator;


	@Before
	public void setUp() throws Exception {
		service=new TransferService();
		createTransferDBOperation=Mockito.mock(CreateTransferOperation.class);
		serviceResponseGenerator=new ServiceResponseGenerator();
		service.setObjectMapper(mapper);
		service.setServiceResponseGenerator(serviceResponseGenerator);
		service.setCreateTransferDBOperation(createTransferDBOperation);
	}


	@Test
	public void performTransferTest() {
		try {
			Mockito.when(createTransferDBOperation.executeOperation(Mockito.any(), Mockito.any())).
			thenReturn(Optional.of(generateResponse()));
			final ServiceResponse response=service.performTransfer(createRequest());
			assertNotNull(response.getResponse());
			assertEquals(200,response.getHttpStatus());
		} 
		catch(MoneyTransferException e) {
			fail(e.getMessage());
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void performTransferTestNoOptional() {
		try {
			Mockito.when(createTransferDBOperation.executeOperation(Mockito.any(), Mockito.any())).
			thenReturn(Optional.empty());
			final ServiceResponse response=service.performTransfer(createRequest());
			fail();
		} 
		catch(MoneyTransferException e) {
			assertNotNull(e.getMessage());
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,e.getHttpStatus());
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void performTransferTestErrorWithAccount() {
		try {
			Mockito.when(createTransferDBOperation.executeOperation(Mockito.any(), Mockito.any())).
			thenThrow(new MoneyTransferException(ErrorType.EA404.getErrorDescription(),ErrorType.EA404.getHttpStatusCode()));
			final ServiceResponse response=service.performTransfer(createRequest());
			fail();
		} 
		catch(MoneyTransferException e) {
			assertNotNull(e.getMessage());
			assertEquals(HttpStatus.NOT_FOUND,e.getHttpStatus());
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void performTransferTestNoBalance() {
		try {
			Mockito.when(createTransferDBOperation.executeOperation(Mockito.any(), Mockito.any())).
			thenThrow(new MoneyTransferException(ErrorType.ET403.getErrorDescription(),ErrorType.ET403.getHttpStatusCode()));
			final ServiceResponse response=service.performTransfer(createRequest());
			fail();
		} 
		catch(MoneyTransferException e) {
			assertNotNull(e.getMessage());
			assertEquals(HttpStatus.FORBIDDEN,e.getHttpStatus());
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
	
	private CreateTransferRequest createRequest() {
		final CreateTransferRequest request=new CreateTransferRequest();
		request.setAmount(200);
		request.setCurrencyType(CreateTransferRequest.CurrencyType.USD);
		request.setDestinationAccountId("2");
		request.setOriginAccountId("1");
		request.setSelfTransfer(false);
		
		return request;
	}

	private TransferResponse generateResponse() {
		final TransferResponse response=new TransferResponse("Test");
		return response;
	}
}
