package com.revolut.money.transfer.controller;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revolut.money.transfer.model.AccountDetails;
import com.revolut.money.transfer.model.AccountType;
import com.revolut.money.transfer.model.CreateAccountRequest;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;

public class AccountsControllerTest {

	private static EmbeddedServer server;
	
	private static HttpClient client;
	
	
	@BeforeClass
    public static void setupServer() {
        server = ApplicationContext.build().run(EmbeddedServer.class); 
        client = server.getApplicationContext().createBean(HttpClient.class, server.getURL()); 
    }
	
	@AfterClass
	public static void stop() {
		if(server!= null) {
			server.stop();
		}
		if(client != null)
			client.close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testCreateAccountOperation() {
		
		final HttpRequest request=HttpRequest.POST("/v1/accounts", createRequest());
		final HttpResponse response=client.toBlocking().exchange(request);
		
		assertEquals(HttpStatus.CREATED, response.getStatus());
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetAccountByIdOperation() {		
		try {
			HttpRequest request=HttpRequest.GET("/v1/accounts/1");
			final String response=client.toBlocking().retrieve(request);		
			
		}
		catch(HttpClientResponseException  ex) {
			assertNotNull(ex);
			assertEquals("Not Found",ex.getMessage());
		}
		
	}
	
	@Test
	public void testUpdateAccountByIdOperation() {		
		try {
			HttpRequest request=HttpRequest.PUT("/v1/accounts/1",createRequest());
			final String response=client.toBlocking().retrieve(request);		
			
		}
		catch(HttpClientResponseException  ex) {
			assertNotNull(ex);
			assertEquals("Method Not Allowed",ex.getMessage());
		}
		
	}
	
	
	private CreateAccountRequest createRequest() {
		CreateAccountRequest request=new CreateAccountRequest();
		request.setAccountType(AccountType.Savings);
		request.setLoginIPAddress("192.168.1.1");
		final AccountDetails details=new AccountDetails();
		details.setAccountBalance(100);
		details.setAccountHolderCountry("USA");
		details.setAccountHolderFirstName("Test");
		details.setAccountHolderLastName("test");
		request.setAccountDetails(details);
		return request;
	}

}
