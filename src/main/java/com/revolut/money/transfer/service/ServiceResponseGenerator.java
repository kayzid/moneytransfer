package com.revolut.money.transfer.service;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.JsonNode;
import com.revolut.money.transfer.model.ServiceResponse;

/**
 * The service response generator class. Which generates a <code>{@link ServiceResponse}</code> 
 * This class is injected into the various service classes to generate the response with response
 * message and the http status code.
 * 
 * 
 * @author Keyur Chitnis
 *
 */
@Singleton
public class ServiceResponseGenerator {

	/**
	 * Method to generate a service response .
	 * 
	 * @param response the reponse
	 * @param httpStatus the status code.
	 * @return ServiceResponse
	 */
	public ServiceResponse generateServiceResponse(JsonNode response,int httpStatus) {
		
		final ServiceResponse serviceResponse=new ServiceResponse(response, httpStatus);
		return serviceResponse;
		
	} 
}
