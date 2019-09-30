package com.revolut.money.transfer.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.revolut.money.transfer.model.CreateTransferRequest;
import com.revolut.money.transfer.service.TransferService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

/**
 * The controller for performing the transfers between accounts.
 * 
 * @author Keyur Chitnis
 *
 */

@Controller("v1/transfers") 
public class MoneyTransferController {
	
	private static final Logger log=LoggerFactory.getLogger(MoneyTransferController.class);
   
	@Inject
	private TransferService transferService;
	
   @Get("/id") 
   public HttpResponse<JsonNode> getTransferInformation(String id) {
       return HttpResponse.ok(transferService.getTransfersById(id).getResponse());
   }
   
   @Post
   public HttpResponse<JsonNode> performTransfer(@Body CreateTransferRequest createTransferRequest) {
	   
	   return HttpResponse.ok(transferService.performTransfer(createTransferRequest).getResponse());
   }
   
   
}
