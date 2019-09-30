package com.revolut.money.transfer.controller;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revolut.money.transfer.model.CreateTransferRequest;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;

public class MoneyTransferControllerTest {
	
	private static EmbeddedServer server;
	
	private static HttpClient client;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 server = ApplicationContext.build().run(EmbeddedServer.class); 
	     client = server.getApplicationContext().createBean(HttpClient.class, server.getURL());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if(server!= null) {
			server.stop();
		}
		if(client != null)
			client.close();
	}

	@Test
	public void performTransferTest() {
		try {
			final HttpRequest request=HttpRequest.POST("/v1/transfers", createRequest());
			final HttpResponse response=client.toBlocking().exchange(request);
		}
		catch(HttpClientResponseException  ex) {
			assertNotNull(ex);
			assertEquals("Not Found",ex.getMessage());
		}
		
		
	}
	
	private CreateTransferRequest createRequest() {
		final CreateTransferRequest request=new CreateTransferRequest();
		request.setAmount(200);
		request.setCurrencyType(CreateTransferRequest.CurrencyType.USD);
		request.setDestinationAccountId("2");
		request.setOriginAccountId("1");
		request.setSelfTransfer(false);
		
		return request;
	}

}
