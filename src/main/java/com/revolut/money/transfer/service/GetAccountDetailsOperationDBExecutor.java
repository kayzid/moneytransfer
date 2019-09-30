package com.revolut.money.transfer.service;

import java.util.Map;
import java.util.Optional;

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
@Named("getAccountDetailsOperationDBExecutor")
public class GetAccountDetailsOperationDBExecutor implements DBOperationExecutor<AccountDetailsResponse> {

	
private static final Logger log=LoggerFactory.getLogger(GetAccountDetailsOperationDBExecutor.class);
	
	@Inject
	private AccountRepository accountRepository;
	
	@Override
	public Optional<AccountDetailsResponse> performDBOperation(Map<String, String> requestParams) throws Exception {
		try {
			Optional<Account> accountOptional=accountRepository.findById(requestParams.get(MoneyTransferConstants.ACCOUNT_ID));
			if(accountOptional.isPresent()) {
				final Account account=accountOptional.get();
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
			else {
				return Optional.empty();
			}
		}
		catch(Exception ex) {
			log.error("fetch-account-details-operation : Error in fetching account ",ex);
			throw ex;
		}
		
	}

}
