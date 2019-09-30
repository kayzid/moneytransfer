package com.revolut.money.transfer.repository;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revolut.money.transfer.repository.domain.Account;

import io.micronaut.context.ApplicationContext;

public class AccountRepositoryImplTest {
	
	AccountRepositoryImpl accountRepository;
	
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em=Mockito.mock(EntityManager.class);
		accountRepository=new AccountRepositoryImpl(em);
	}

	

	@Test
	public void findByIdTest() {
		
		Account account=accountRepository.saveAccount(generateAccount());		
		assertNotNull(account);
		Mockito.when(em.find(Mockito.any(), Mockito.any())).thenReturn(account);
		Optional<Account> response=accountRepository.findById("1");
		assertTrue(response.isPresent());
		assertNotNull(response.get());
		assertEquals("1",response.get().getAccountId());
	}
	
	@Test
	public void findByIdNoElementTest() {
		Mockito.when(em.find(Mockito.any(), Mockito.any())).thenReturn(null);
		Optional<Account> response=accountRepository.findById("1");
		assertFalse(response.isPresent());
		
	}
	
	@Test
	public void findByIdExceptionTest() {

		try {
			Mockito.when(em.find(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException("test"));
			Optional<Account> response=accountRepository.findById("1");
		}
		catch(Exception ex) {
			assertNotNull(ex);
		}
	}
	
	
	@Test
	public void saveAccount() {
		Account account=accountRepository.saveAccount(generateAccount());		
		assertNotNull(account);
		
	}
	
	
	@Test
	public void updateAccountById() {
		Query mockQuery=Mockito.mock(Query.class);
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(mockQuery);
		Mockito.when(mockQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockQuery);
		Mockito.when(mockQuery.executeUpdate()).thenReturn(1);
				
		
		int response=accountRepository.updateAccountBalanceById("Test", 200.0);
		assertEquals(1,response);
	}

	
	@Test
	public void updateAccountByIdWithException() {
		Query mockQuery=Mockito.mock(Query.class);
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(mockQuery);
		Mockito.when(mockQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockQuery);
		Mockito.when(mockQuery.executeUpdate()).thenThrow(new RuntimeException("test"));
		
		try {
			int response=accountRepository.updateAccountBalanceById("Test", 200);
			fail();
		}
		catch(Exception ex) {
			assertNotNull(ex);
		}
		
		
	}
	
	@Test
	public void deleteAccountById() {
		Mockito.when(em.find(Mockito.any(), Mockito.any())).thenReturn(generateAccount());
		
		boolean isSccessful=accountRepository.deleteAccountById("Test");
		assertTrue(isSccessful);
	}
	
	@Test
	public void deleteAccountByIdNoAccount() {
		Mockito.when(em.find(Mockito.any(), Mockito.any())).thenReturn(null);
		
		boolean isSccessful=accountRepository.deleteAccountById("Test");
		assertFalse(isSccessful);
	}
	
	@Test
	public void getAccountBalanceTest() {
		@SuppressWarnings("unchecked")
		TypedQuery<Object> mockQuery=Mockito.mock(TypedQuery.class);
		Mockito.when(em.createQuery(Mockito.anyString(), Mockito.any())).thenReturn(mockQuery);
		Mockito.when(mockQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockQuery);
		Mockito.when(mockQuery.getResultList()).thenReturn(Arrays.asList(2d));
		Optional<Double> responseDouble=accountRepository.getAccountBalance("Test");
		assertTrue(responseDouble.isPresent());
	}
	
	@Test
	public void getAccountBalanceTestEmptyList() {
		@SuppressWarnings("unchecked")
		TypedQuery<Object> mockQuery=Mockito.mock(TypedQuery.class);
		Mockito.when(em.createQuery(Mockito.anyString(), Mockito.any())).thenReturn(mockQuery);
		Mockito.when(mockQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockQuery);
		Mockito.when(mockQuery.getResultList()).thenReturn(Arrays.asList());
		Optional<Double> responseDouble=accountRepository.getAccountBalance("Test");
		assertTrue(!responseDouble.isPresent());
	}
	
	@Test
	public void getAccountBalanceTestNullList() {
		@SuppressWarnings("unchecked")
		TypedQuery<Object> mockQuery=Mockito.mock(TypedQuery.class);
		Mockito.when(em.createQuery(Mockito.anyString(), Mockito.any())).thenReturn(mockQuery);
		Mockito.when(mockQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockQuery);
		Mockito.when(mockQuery.getResultList()).thenReturn(null);
		Optional<Double> responseDouble=accountRepository.getAccountBalance("Test");
		assertTrue(!responseDouble.isPresent());
	}
	
	
	
	private Account generateAccount() {
		final Account account=new Account();
		account.setAccountBalance(200);
		account.setAccountHolderCountry("USA");
		account.setAccountHolderFirstName("Test");
		account.setAccountHolderLastName("Test");
		account.setAccountId("1");
		account.setAccountLastLoginDate(Instant.now().toEpochMilli());
		account.setAccountType("Savings");
		account.setCreatedDate(Instant.now().toEpochMilli());
		account.setAccountLastLoginIPAddress("192.168.1.1");
		account.setUpdatedDate(Instant.now().toEpochMilli());
		return account;
	}

}
