package com.revolut.money.transfer.service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.money.transfer.MoneyTransferConstants;
import com.revolut.money.transfer.exceptions.MoneyTransferException;
import com.revolut.money.transfer.model.TransferResponse;
import com.revolut.money.transfer.repository.AccountRepository;

/**
 * The DB Operation Class for performing money transfer.
 * This class implements the <code> com.revolut.money.transfer.service.DBOperationExecutor </code> class and passes it
 * a <code> com.revolut.money.transfer.model.TransferResponse </code> which generates the appropriate transfer response.
 * 
 * TO DO
 * A future enhancement to log the transactions in a transcations table. Currently
 * the transcation is being logged in the logs which can be referred to 
 * for generating a transaction history for accounts.
 * 
 * @author Keyur Chitnis
 *
 */


@Singleton
public class CreateTransferOperationDBExecutor implements DBOperationExecutor<TransferResponse> {

	private static final Logger log=LoggerFactory.getLogger(CreateTransferOperationDBExecutor.class);
	private static final String CREDIT="Credit";
	private static final String DEBIT="Debit";
	
	@Inject
	private AccountRepository accountRepository;
	
	
	/**
	 * Method to perform the money transfer between the accounts
	 * or debit or credit an amount in same account. This method will validate
	 * balance in the accounts incase of transfers or when an amount is being deducted from the account.
	 * 
	 * @param requestParams the hash map of request params for completing the operation
	 * @throws Exception exception incase of any runtime exceptions.
	 * @return Optional<TransferResponse> the optional response. 
	 * 
	 */
	@Override
	public Optional<TransferResponse> performDBOperation(Map<String, String> requestParams) throws Exception {
		try {
			final String originAcountId=requestParams.get(MoneyTransferConstants.ORIGINATION_ACCOUNT_ID);
			double amountToDeposit=Double.parseDouble(requestParams.get(MoneyTransferConstants.AMOUNT));
			
			Optional<Double> optionalBalance=getBalanceInAccount(originAcountId);
			
			if(!optionalBalance.isPresent()) {
				log.error("perform-transfer: Account not found for id {}",originAcountId);
				throw new MoneyTransferException(ErrorType.EA404.getErrorDescription(),ErrorType.EA404.getHttpStatusCode());			
			}
			
			double balanceInOriginAccount=optionalBalance.get();
			if(Boolean.valueOf(requestParams.get(MoneyTransferConstants.SELF_TRANSFER))) {
				if(CREDIT.equals(requestParams.get(MoneyTransferConstants.TRANSFER_TYPE))) {					
					balanceInOriginAccount+=amountToDeposit;					
				}
				else {
					checkIfAccountHasSufficientBalance(originAcountId, amountToDeposit, balanceInOriginAccount);
					balanceInOriginAccount-=amountToDeposit;										
				}
				
				updateMoneyInAccount(originAcountId, balanceInOriginAccount);	
				String transactionId=generateTransactionId();
				log.info("perform-transfer: {} - Transfer completed successfully origin {} for amount {}",new Object[] {transactionId,originAcountId,amountToDeposit});
				return generateResponse();
			}
			else {
				checkIfAccountHasSufficientBalance(originAcountId, amountToDeposit, balanceInOriginAccount);
				
				final String destinationAccountId=requestParams.get(MoneyTransferConstants.DESTINATION_ACCOUNT_ID);
				
				Optional<Double> distinationAccountBalance=getBalanceInAccount(destinationAccountId);
				
				if(!distinationAccountBalance.isPresent()) {
					log.error("perform-transfer: Account not found for id {}",destinationAccountId);
					throw new MoneyTransferException(ErrorType.EA404.getErrorDescription(),ErrorType.EA404.getHttpStatusCode());
				}
				
				double newBalance=distinationAccountBalance.get() + amountToDeposit;
				balanceInOriginAccount-=amountToDeposit;
				
				updateMoneyInAccount(originAcountId, balanceInOriginAccount);
				
				updateMoneyInAccount(destinationAccountId, newBalance);
				
				String transactionId=generateTransactionId();
				log.info("perform-transfer: {} - Transfer completed successfully between origin {} - destination {} for amount {}",new Object[] {transactionId,originAcountId,destinationAccountId,amountToDeposit});
				return generateResponse();
			}
			
		}
		catch(MoneyTransferException ex) {
			throw ex;
		}
		catch(Exception ex) {
			log.error("create-transfer-operation: Error in executing transfer : ",ex);
			throw ex;
		}
		
	}

	/**
	 * Method to generate a trnasaction ID
	 * @return the string UUID.
	 */
	private String generateTransactionId() {
		return UUID.randomUUID().toString();
	}

	/**
	 * The method to check balance in the account
	 * 
	 * @param accountId the Account id to get balance for.
	 * @return Optional<Double> the optional balance in the account. 
	 */
	private Optional<Double> getBalanceInAccount(final String accountId) {
		return accountRepository.getAccountBalance(accountId);
	}

	/**
	 * Generate the response object.
	 * 
	 * @return Optional<TransferResponse> the optional transferResponse
	 */
	private Optional<TransferResponse> generateResponse() {
		final TransferResponse response=new TransferResponse("Transfer completed successfully");
		return Optional.of(response);
	}
	

	/**
	 * Method to update the money in the account.
	 * 
	 * @param accountId The accountId to update balance in.
	 * @param newBalance The  new balance in the account.
	 */
	private void updateMoneyInAccount(final String accountId, double newBalance) {
		accountRepository.updateAccountBalanceById(accountId, 
				newBalance);
	}
	
	

	/**
	 * The method to check if account has sufficient balance
	 * 
	 * @param accountId the account id
	 * @param amountToDeposit the amount to deposit.
	 * @param balanceInOriginAccount the balance on origin account.
	 * 
	 * @throws MoneyTransferException the run time money transfer exception.
	 */
	private void checkIfAccountHasSufficientBalance(final String accountId, double amountToDeposit,
			double balanceInOriginAccount) {
		if(balanceInOriginAccount < amountToDeposit) {
			log.error("perform-transfer: Account  {} contains insufficient balance ",accountId);
			throw new MoneyTransferException(ErrorType.ET403.getErrorDescription(),ErrorType.ET403.getHttpStatusCode());
		}
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
