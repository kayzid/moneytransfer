package com.revolut.money.transfer.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.exceptions.MoneyTransferException;
import com.revolut.money.transfer.model.TransferResponse;
import com.revolut.money.transfer.repository.AccountRepository;
import com.revolut.money.transfer.repository.AccountRepositoryImpl;

import io.micronaut.http.HttpStatus;

public class CreateTransferOperationDBExecutorTest {

	CreateTransferOperationDBExecutor executor;
	private AccountRepository accountRepository;
	
	@Before
	public void setUp() throws Exception {
		executor=new CreateTransferOperationDBExecutor();
		accountRepository=Mockito.mock(AccountRepositoryImpl.class);
		executor.setAccountRepository(accountRepository);
	}

	@Test
	public void performDBOperationTest() {
		
		try {
			Mockito.when(accountRepository.getAccountBalance(Mockito.anyString())).thenReturn(Optional.of(new Double(200)));
			Mockito.when(accountRepository.updateAccountBalanceById(Mockito.anyString(), Mockito.anyDouble())).thenReturn(1);
			Optional<TransferResponse> responseOptional=executor.performDBOperation(generateMap(true,"Credit"));
			assertTrue(responseOptional.isPresent());
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void performDBOperationTestNoAccount() {
		
		try {
			Mockito.when(accountRepository.getAccountBalance(Mockito.anyString())).thenReturn(Optional.empty());
			Mockito.when(accountRepository.updateAccountBalanceById(Mockito.anyString(), Mockito.anyDouble())).thenReturn(1);
			Optional<TransferResponse> responseOptional=executor.performDBOperation(generateMap(true,"Credit"));
			fail();
			
		} 
		catch(MoneyTransferException ex) {
			assertEquals(HttpStatus.NOT_FOUND,ex.getHttpStatus());
			assertNotNull(ex.getMessage());
		}
		catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void performDBOperationTestInsufficentBalanceAccount() {
		
		try {
			Mockito.when(accountRepository.getAccountBalance(Mockito.anyString())).thenReturn(Optional.of(new Double(1)));
			Mockito.when(accountRepository.updateAccountBalanceById(Mockito.anyString(), Mockito.anyDouble())).thenReturn(1);
			Optional<TransferResponse> responseOptional=executor.performDBOperation(generateMap(true,"Debit"));
			fail();
			
		} 
		catch(MoneyTransferException ex) {
			assertEquals(HttpStatus.FORBIDDEN,ex.getHttpStatus());
			assertNotNull(ex.getMessage());
		}
		catch (Exception e) {
			fail();
		}
	}

	
	@Test
	public void performDBOperationTestInsufficentBalanceTransferBetweenAccount() {
		
		try {
			Mockito.when(accountRepository.getAccountBalance(Mockito.anyString())).thenReturn(Optional.of(new Double(1)));
			Mockito.when(accountRepository.updateAccountBalanceById(Mockito.anyString(), Mockito.anyDouble())).thenReturn(1);
			Optional<TransferResponse> responseOptional=executor.performDBOperation(generateMap(false,"Debit"));
			fail();
			
		} 
		catch(MoneyTransferException ex) {
			assertEquals(HttpStatus.FORBIDDEN,ex.getHttpStatus());
			assertNotNull(ex.getMessage());
		}
		catch (Exception e) {
			fail();
		}
	}

	
	@Test
	public void performDBOperationTestNoAccountTransferBetweenAccount() {
		
		try {
			Mockito.when(accountRepository.getAccountBalance(Mockito.anyString())).thenReturn(Optional.of(new Double(1000))).thenReturn(Optional.empty());
			Mockito.when(accountRepository.updateAccountBalanceById(Mockito.anyString(), Mockito.anyDouble())).thenReturn(1);
			
			Optional<TransferResponse> responseOptional=executor.performDBOperation(generateMap(false,"Debit"));
			fail();
			
		} 
		catch(MoneyTransferException ex) {
			assertEquals(HttpStatus.NOT_FOUND,ex.getHttpStatus());
			assertNotNull(ex.getMessage());
		}
		catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void performDBOperationTestSuccessfulTransferBetweenAccount() {
		
		try {
			Mockito.when(accountRepository.getAccountBalance(Mockito.anyString())).thenReturn(Optional.of(new Double(1000))).thenReturn(Optional.of(new Double(200)));
			Mockito.when(accountRepository.updateAccountBalanceById(Mockito.anyString(), Mockito.anyDouble())).thenReturn(1).thenReturn(1);
			
			Optional<TransferResponse> responseOptional=executor.performDBOperation(generateMap(false,"Debit"));
			assertTrue(responseOptional.isPresent());
			
		} 
		catch(MoneyTransferException ex) {
			fail();
		}
		catch (Exception e) {
			fail();
		}
	}

	
	private Map<String,String> generateMap(boolean isSelfTransfer,String transferType){
		final Map<String,String> requestParamsKvP=new HashMap<>();
		requestParamsKvP.put(MoneyTransferConstants.ORIGINATION_ACCOUNT_ID,"1");
		requestParamsKvP.put(MoneyTransferConstants.DESTINATION_ACCOUNT_ID,"2");
		requestParamsKvP.put(MoneyTransferConstants.AMOUNT,Double.toString(100));
		if(isSelfTransfer) {
			requestParamsKvP.put(MoneyTransferConstants.TRANSFER_TYPE,transferType);
			requestParamsKvP.put(MoneyTransferConstants.SELF_TRANSFER, Boolean.toString(isSelfTransfer));
		}
		
		
		return requestParamsKvP;
		
	}
}
