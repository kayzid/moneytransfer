package com.revolut.money.transfer.service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.model.AccountDetailsResponse;
import com.revolut.money.transfer.repository.AccountRepository;
import com.revolut.money.transfer.repository.domain.Account;

@Singleton
@Named("createAccountOperation")
public class CreateAccountsOperationDBExecutor implements DBOperationExecutor<AccountDetailsResponse> {

	private static final Logger log=LoggerFactory.getLogger(CreateAccountsOperationDBExecutor.class);
	
	@Inject
	private AccountRepository accountRepository;
	
	@Override
	public Optional<AccountDetailsResponse> performDBOperation(Map<String,String> requestParams) throws Exception {
		
		try {
			final Account account=new Account();
			account.setAccountHolderFirstName(requestParams.get(MoneyTransferConstants.ACCOUNT_HOLDER_FIRST_NAME));
			account.setAccountHolderLastName(requestParams.get(MoneyTransferConstants.ACCOUNT_HOLDER_LAST_NAME));
			account.setAccountId(UUID.randomUUID().toString());
			account.setCreatedDate(Instant.now().toEpochMilli());
			account.setUpdatedDate(Instant.now().toEpochMilli());
			account.setAccountBalance(Double.parseDouble(requestParams.get(MoneyTransferConstants.ACCOUNT_HOLDER_BALANCE)));
			account.setAccountLastLoginIPAddress(requestParams.get(MoneyTransferConstants.LAST_LOGIN_IP));
			account.setAccountLastLoginDate(Instant.now().toEpochMilli());
			account.setAccountHolderCountry(requestParams.get(MoneyTransferConstants.ACCOUNT_HOLDER_COUNTRY));
			account.setAccountType(requestParams.get(MoneyTransferConstants.ACCOUNT_TYPE));
			
			accountRepository.saveAccount(account);
			
			final AccountDetailsResponse response=new AccountDetailsResponse();
			response.setAccountHolderFirstName(account.getAccountHolderFirstName());
			response.setAccountHolderLastName(account.getAccountHolderLastName());
			response.setAccountId(account.getAccountId());
			response.setCreatedDate(account.getCreatedDate());
			response.setUpdatedDate(account.getUpdatedDate());
			response.setAccountBalance(account.getAccountBalance());
			response.setAccountLastLoginDate(account.getAccountLastLoginDate());
			response.setAccountHolderCountry(account.getAccountHolderCountry());
			response.setAccountType(account.getAccountType());
			response.setAccountLastLoginIPAddress(account.getAccountLastLoginIPAddress());
			
			return Optional.of(response);
		}
		catch(Exception ex) {
			log.error("create-account-operation : Error in creating account ",ex);
			throw ex;
		}
		
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
