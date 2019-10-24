package com.aot.forms.formApi;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.web.client.RestTemplate;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class CamundaServices {
	
	public boolean getTask() {
	
		/*
		 * final String uri = "http://localhost:9092/camunda/engine-rest/task";
		 * 
		 * RestTemplate restTemplate = new RestTemplate();
		 * 
		 * String result = restTemplate.getForObject(uri, String.class);
		 * System.out.print("result of REST API call : " + result);
		 */
		
		final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setClientId("camunda-api-client");
		resourceDetails.setClientSecret("ed641cbf-d23f-4fdc-bdf3-949108c83cd2");
		resourceDetails.setGrantType("authorization_code");
		resourceDetails
				.setAccessTokenUri("https://localhost:9001/auth/realms/orbeonRealm/protocol/openid-connect/token");

		final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
		final OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();

		final String accessTokenAsString = accessToken.getValue();

	
		
		
		return true;
	}
}
