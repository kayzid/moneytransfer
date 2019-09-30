package com.revolut.money.transfer.service;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.model.AccountDetailsResponse;
import com.revolut.money.transfer.repository.AccountRepository;
import com.revolut.money.transfer.repository.AccountRepositoryImpl;
import com.revolut.money.transfer.repository.domain.Account;

public class GetAccountDetailsOperationDBExecutorTest {
	GetAccountDetailsOperationDBExecutor executor;
	private AccountRepository accountRepository;
	

	@Before
	public void setUp() throws Exception {
		executor=new GetAccountDetailsOperationDBExecutor();
		accountRepository=Mockito.mock(AccountRepositoryImpl.class);
		executor.setAccountRepository(accountRepository);
	}



	@Test
	public void performDBOperationTest() {
		
		try {
			Mockito.when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.of(accountGenerator()));
			Optional<AccountDetailsResponse> responseOptional=executor.performDBOperation(generateMap());
			assertTrue(responseOptional.isPresent());
		} catch (Exception e) {
			fail();
		}
	}
	

	@Test
	public void performDBOperationEmptyTest() {
		
		try {
			Mockito.when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
			Optional<AccountDetailsResponse> responseOptional=executor.performDBOperation(generateMap());
			assertTrue(!responseOptional.isPresent());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void performDBOperationExceptionTest() {
		
		try {
			Mockito.when(accountRepository.findById(Mockito.anyString())).thenThrow(new RuntimeException("test"));
			Optional<AccountDetailsResponse> responseOptional=executor.performDBOperation(generateMap());
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	
	private Map<String,String> generateMap(){
		final Map<String,String> requestParams=new HashMap<>();
		requestParams.put(MoneyTransferConstants.ACCOUNT_ID, "AccountId");
		return requestParams;
		
	}
	
	private Account accountGenerator() {
		final Account account=new Account();
		account.setAccountBalance(200);
		account.setAccountHolderCountry("USA");
		account.setAccountHolderFirstName("Test");
		account.setAccountHolderLastName("Test");
		account.setCreatedDate(Instant.now().toEpochMilli());
		account.setUpdatedDate(Instant.now().toEpochMilli());
		account.setAccountType("Savings");
		return account;
		
	}
}
