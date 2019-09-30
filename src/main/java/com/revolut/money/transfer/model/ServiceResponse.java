package com.revolut.money.transfer.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * The response class which contains 
 * the json node response and the http status code.
 * 
 * 
 * @author Keyur Chitnis
 *
 */
public class ServiceResponse {
	
	private JsonNode response;
	private int httpStatus;

	public ServiceResponse(JsonNode response,int httpStatus) {
		this.httpStatus=httpStatus;
		this.response=response;
	}

	public JsonNode getResponse() {
		return response;
	}

	public int getHttpStatus() {
		return httpStatus;
	}
	
	
}
