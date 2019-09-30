/*package com.revolut.money.transfer.repository;

import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.revolut.money.transfer.repository.domain.Transaction;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class TransactionRepositoryImpl implements TransactionRespository {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public TransactionRepositoryImpl(@CurrentSession EntityManager em) {
		this.em=em;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Transaction> findById(String transferId) {
		return Optional.ofNullable(em.find(Transaction.class, transferId));
	}

	@Override
	public Transaction saveTransfer(Transaction transfer) {
		em.persist(transfer);
		return transfer;
	}

	@Override
	public Optional<List<Transaction>> findAll() {
		return Optional.ofNullable(em.createQuery("SELECT t from Transaction t",Transaction.class).getResultList());
	}

}
*/