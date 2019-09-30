package com.revolut.money.transfer.controller;

import javax.inject.Inject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.revolut.money.transfer.model.CreateAccountRequest;
import com.revolut.money.transfer.model.ServiceResponse;
import com.revolut.money.transfer.model.UpdateAccountRequest;
import com.revolut.money.transfer.service.AccountsService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;


/**
 * 
 * The controller class  for handling different operations
 * on accouns table.
 * 
 * 
 * @author Keyur Chitnis
 *
 */
@Controller("v1/accounts")
public class AccountsController {
	
	private static final Logger log=LoggerFactory.getLogger(AccountsController.class);
	
	@Inject
	private AccountsService accountService;
	
	
	@Get("/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public HttpResponse<JsonNode> getAccountById(String accountId) {
		ServiceResponse response=accountService.findAccountsById(accountId);
		return HttpResponse.ok(response.getResponse());
	}
	
	@Put("/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public HttpResponse<JsonNode> updateAccount(String accountId,@Body UpdateAccountRequest updateAccountRequest) {
		ServiceResponse response=accountService.updateAccount(updateAccountRequest, accountId);
		return HttpResponse.ok(response.getResponse());
	}
	
	@Post
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public HttpResponse<JsonNode> createAccount(@Body CreateAccountRequest createAccountRequest) {
		log.info("create-account-request: {}",createAccountRequest.getAccountDetails().getAccountHolderFirstName());
		ServiceResponse response=accountService.createAccount(createAccountRequest);
		return HttpResponse.created(response.getResponse());
	}
	
	

}
