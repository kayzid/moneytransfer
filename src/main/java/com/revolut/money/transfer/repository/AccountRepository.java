package com.revolut.money.transfer.repository;

import java.util.List;
import java.util.Optional;
import com.revolut.money.transfer.repository.domain.Account;

/**
 * 
 * The base interface for supporting operations
 * on the account table.
 * 
 * @author Keyur Chitnis
 *
 */
public interface AccountRepository {

	public Optional<Account> findById(String accountId);
	public Account saveAccount(Account account);
	public Optional<Double> getAccountBalance(String accountId);
	public int updateAccountBalanceById(String accountId, double balance);
	public boolean deleteAccountById(String accountId);
}
