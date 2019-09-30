package com.revolut.money.transfer.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.revolut.money.transfer.repository.domain.Account;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * The Class which provides concrete implementations for various CURD operations
 * on the accounts table.
 * 
 * @author Keyur Chitnis
 *
 */
@Singleton
public class AccountRepositoryImpl implements AccountRepository {
	
	private static final String ACCOUNT_ID_CAMEL_CASE="accountId";
	
	@Inject
	@PersistenceContext
	private EntityManager em;
	
	public AccountRepositoryImpl(@CurrentSession EntityManager em) {
		this.em=em;
	}

	/**
	 * The method to find an account by id.
	 */
	@Override
	@Transactional(readOnly=true)
	public Optional<Account> findById(String accountId) {
		return Optional.ofNullable(em.find(Account.class, accountId));
		
	}

	/**
	 * The method to create a new account.
	 */
	@Override
	@Transactional
	public Account saveAccount(Account account) {		
		em.persist(account);
		return account;
	}

	/**
	 * The method to update the accountBalance by account id.
	 */
	@Override
	@Transactional
	public int updateAccountBalanceById(String accountId, double balance) {
		return em.createQuery("Update Account a SET accountBalance = :balance, updatedDate = :updateDate  where accountId = :accountId")
		.setParameter("balance", balance)
		.setParameter("updateDate", Instant.now().toEpochMilli())
		.setParameter(ACCOUNT_ID_CAMEL_CASE, accountId).executeUpdate();
	}

	/**
	 * The method to delete account given an id.
	 */
	@Override
	@Transactional
	public boolean deleteAccountById(String accountId) {
		Optional<Account> optionalAccount=findById(accountId);
		if(optionalAccount.isPresent()) {
			em.remove(optionalAccount.get());
			return true;
		}
		
		return false;
		
		
	}

	/**
	 * Method to get balance in an account given an account id.
	 */
	@Override
	@Transactional(readOnly=true)
	public Optional<Double> getAccountBalance(String accountId) {
		List<Double> balance=em.createQuery("SELECT a.accountBalance from Account a where a.accountId = :accountId",Double.class)
				.setParameter(ACCOUNT_ID_CAMEL_CASE, accountId).getResultList();
		if(balance == null || balance.size() < 1)
			return Optional.empty();
		
		return Optional.of(balance.get(0)); 
	}	

}
