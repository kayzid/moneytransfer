package com.revolut.money.transfer.service;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.model.AccountDetailsResponse;
import com.revolut.money.transfer.repository.AccountRepository;
import com.revolut.money.transfer.repository.AccountRepositoryImpl;
import com.revolut.money.transfer.repository.domain.Account;

public class CreateAccountsOperationDBExecutorTest {

	CreateAccountsOperationDBExecutor executor;
	private AccountRepository accountRepository;
	
	
	@Before
	public void setUp() throws Exception {
		accountRepository=Mockito.mock(AccountRepositoryImpl.class);
		executor=new CreateAccountsOperationDBExecutor();
		executor.setAccountRepository(accountRepository);
	}

	@Test
	public void performDBOperationTest() {
		try {
			Mockito.when(accountRepository.saveAccount(Mockito.any())).thenReturn(generateAccount());
			Optional<AccountDetailsResponse> optionalResponse=executor.performDBOperation(generateMap());
			assertTrue(optionalResponse.isPresent());
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void performDBOperationWithExceptionTest() {
		try {
			Mockito.when(accountRepository.saveAccount(Mockito.any())).thenThrow(new RuntimeException("Test"));
			Optional<AccountDetailsResponse> optionalResponse=executor.performDBOperation(generateMap());
			fail();
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
		
	}

	private Account generateAccount() {
		final Account account=new Account();
		account.setAccountBalance(200);
		account.setAccountHolderCountry("USA");
		account.setAccountHolderFirstName("Test");
		account.setAccountHolderLastName("Test");
		account.setAccountId("Test");
		account.setAccountLastLoginDate(Instant.now().toEpochMilli());
		account.setCreatedDate(Instant.now().toEpochMilli());
		account.setUpdatedDate(Instant.now().toEpochMilli());
		account.setAccountType("Checking");
		account.setAccountLastLoginIPAddress("192.168.1.1");
		return account;
		
		
	}
	
	private Map<String,String> generateMap(){
		final Map<String,String> requestParamsKvP=new HashMap<>();
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_HOLDER_FIRST_NAME, "test");
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_HOLDER_LAST_NAME, "test");
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_HOLDER_BALANCE, Double.toString(200));
		requestParamsKvP.put(MoneyTransferConstants.LAST_LOGIN_IP, "192.168.1.1");
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_HOLDER_COUNTRY, "USA");
		requestParamsKvP.put(MoneyTransferConstants.ACCOUNT_TYPE, "Checking");
		
		
		return requestParamsKvP;
	}
}
