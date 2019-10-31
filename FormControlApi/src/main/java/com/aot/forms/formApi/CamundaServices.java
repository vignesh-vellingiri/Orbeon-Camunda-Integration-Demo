package com.aot.forms.formApi;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.aot.forms.exception.RestTemplateException;
import com.aot.forms.model.CamundaTaskResp;
import com.aot.forms.rest.RequestResponseLoggingInterceptor;
import com.aot.forms.rest.RestTemplateResponseErrorHandler;
import com.aot.forms.repository.OrbeonMetaDataRepository;

@Component
@Import({CamundaTaskResp.class})
public class CamundaServices {
	@Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;
	
	@Autowired
	private OrbeonMetaDataRepository orbeonMetaDataRepository;
	
	private int timeout = 5000;
	
	private CamundaTaskResp[] camundaTaskReq;
	
	@Value("${camunda.rest.baseUrl}")
	private String CAMUNDA_BASE_URL;

    /**
     * @param groups
     * @return
     */
	
	public void configureRestTemplate() {
		this.oAuth2RestTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		this.oAuth2RestTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
    	 RequestConfig config = RequestConfig.custom()
    		      .setConnectTimeout(this.timeout)
    		      .setConnectionRequestTimeout(this.timeout)
    		      .setSocketTimeout(this.timeout)
    		      .build();
    	CloseableHttpClient client = HttpClientBuilder
    		      .create()
    		      .setDefaultRequestConfig(config)
    		      .build();
    	HttpComponentsClientHttpRequestFactory clientHttpRequestFactory  = new HttpComponentsClientHttpRequestFactory(client);
    	this.oAuth2RestTemplate.setRequestFactory(clientHttpRequestFactory);
	}
	
	
    public int getTimeout() {
		return timeout;
	}


	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	// Get task matching the task Id
	public CamundaTaskResp getTasks(String taskId) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	JSONObject variableJsonObject = new JSONObject();
    	CamundaTaskResp taskResponse = null ;
    	String documentValue =null;
    	configureRestTemplate();
    	{
			try {
	    		
				taskResponse = oAuth2RestTemplate.getForObject(CAMUNDA_BASE_URL + "/task/" + taskId , CamundaTaskResp.class);
				
				// Get Orbeon document ID from the variables of corresponding process instance,
				String processInstanceId = taskResponse.getProcessInstanceId();
				variableJsonObject = new JSONObject(oAuth2RestTemplate.getForObject(CAMUNDA_BASE_URL + "/process-instance/" + processInstanceId + "/variables", String.class));
				documentValue = variableJsonObject.getJSONObject("document").getString("value");
				taskResponse.setOrbeonDocumentId(documentValue);
				OrbeonMetaData omd = orbeonMetaDataRepository.findByCamundaIdEquals(processInstanceId);
				taskResponse.setStatus(omd.getStatus());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	return taskResponse;
    }
	
	// Get task for the given group
	public CamundaTaskResp[] getTasks(List<String> groups, String userName) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	JSONObject taskJsonObject = new JSONObject();
    	CamundaTaskResp[] taskResponse ;
    	configureRestTemplate();
    	camundaTaskReq = new CamundaTaskResp[0];
    	for (String group : groups) {
			try {
	    		taskJsonObject.put("candidateGroup", group.replaceAll("/", ""));
				HttpEntity<String> request = new HttpEntity<String>(taskJsonObject.toString(),headers);
				taskResponse = oAuth2RestTemplate.postForObject(CAMUNDA_BASE_URL + "/task", request, CamundaTaskResp[].class);
		    	if(taskResponse.length > 0)
		    		camundaTaskReq = ArrayUtils.addAll(camundaTaskReq,taskResponse);
		    	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
    	taskResponse = oAuth2RestTemplate.getForObject(CAMUNDA_BASE_URL + "/task?assignee=" + userName, CamundaTaskResp[].class);
    	if(taskResponse.length > 0)
    		camundaTaskReq = ArrayUtils.addAll(camundaTaskReq,taskResponse);
    	
    	int index = 0;
    	for(CamundaTaskResp ctr : camundaTaskReq) {
    		OrbeonMetaData omd = orbeonMetaDataRepository.findByCamundaIdEquals(ctr.getProcessInstanceId());
    		camundaTaskReq[index++].setStatus(omd.getStatus());
    	}
    	return camundaTaskReq ;
    }
    
    public String claimTasks(String taskId, String user) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	JSONObject taskJsonObject = new JSONObject();
    	String claimTaskUrl ;
    	String processInstanceId;
    	configureRestTemplate();
    	try {
    		taskJsonObject.put("userId", user);
    		HttpEntity<String> request = new HttpEntity<String>(taskJsonObject.toString(),headers);
    		if(taskId != null && taskId != "" && user != null && user != "")
    			claimTaskUrl = CAMUNDA_BASE_URL + "/task/" + taskId + "/claim";
    		else 
    			return "Task Id or User can't be empty. Check TaskId or authentication.";
    		ResponseEntity<String> response  = oAuth2RestTemplate.postForEntity(claimTaskUrl, request, String.class);
    		
    		response  = oAuth2RestTemplate.getForEntity(claimTaskUrl.replace("/claim", ""), String.class);
    		JSONObject json = new JSONObject(response.getBody());
    		processInstanceId = (String) json.get("processInstanceId");
    		OrbeonMetaData omd = orbeonMetaDataRepository.findByCamundaIdEquals(processInstanceId);
	    	omd.setStatus("IN-PROGRESS");
	    	orbeonMetaDataRepository.save(omd);
    		System.out.println("dummy");
    	}
    	catch(RestTemplateException rte) {
    		return rte.getError();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return "Exception during claim task. Please reach out to support team.";
    	}
    	
    	return "OK";
    	
    }
    
    public String completeTasks(String taskId, String user) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	JSONObject taskJsonObject = new JSONObject();
    	String claimTaskUrl ;
    	String processInstanceId;
    	configureRestTemplate();
    	try {
    		taskJsonObject.put("userId", user);
    		HttpEntity<String> request = new HttpEntity<String>(taskJsonObject.toString(),headers);
    		if(taskId != null && taskId != "" && user != null && user != "")
    			claimTaskUrl = CAMUNDA_BASE_URL + "/task/" + taskId + "/complete";
    		else 
    			return "Task Id or User can't be empty. Check TaskId or authentication.";
    		ResponseEntity<String> response  = oAuth2RestTemplate.postForEntity(claimTaskUrl, request, String.class);
    		
//    		response  = oAuth2RestTemplate.getForEntity(claimTaskUrl.replace("/claim", ""), String.class);
//    		JSONObject json = new JSONObject(response.getBody());
//    		processInstanceId = (String) json.get("processInstanceId");
//    		OrbeonMetaData omd = orbeonMetaDataRepository.findByCamundaIdEquals(processInstanceId);
//	    	omd.setStatus("IN-PROGRESS");
//	    	orbeonMetaDataRepository.save(omd);
//    		System.out.println("dummy");
    	}
    	catch(RestTemplateException rte) {
    		return rte.getError();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return "Exception during task completion. Please reach out to support team.";
    	}
    	
    	return "OK";
    	
    }
    
    public String startProcessDefinition(HashMap<String, String> variableHM, String processDefinitionKey, OrbeonMetaData omd) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	JSONObject variableJsonObject = new JSONObject();
    	JSONObject variableValueJsonObject = new JSONObject();
    	JSONObject variableListJsonObject = new JSONObject();
    	JSONObject tempObject = new JSONObject();
    	String StartProcessDefinitionUrl ;
    	String processInstanceId = "";
    	configureRestTemplate();
    	try {
    		for (Map.Entry mapElement : variableHM.entrySet()) {
    			variableValueJsonObject.put("value",  (String)mapElement.getValue());
    			tempObject = new JSONObject(variableValueJsonObject.toString());
    			variableValueJsonObject.remove("value");
    			variableJsonObject.put( (String)mapElement.getKey(), tempObject );
    			variableListJsonObject.put("variables", variableJsonObject);
    			
    		}
    		
    		HttpEntity<String> request = new HttpEntity<String>(variableListJsonObject.toString(),headers);
    		if(processDefinitionKey != null && processDefinitionKey != "" )
    			StartProcessDefinitionUrl = CAMUNDA_BASE_URL + "/process-definition/key/" + processDefinitionKey + "/start";
    		else 
    			return "process DefinitionKey can't be empty.";
    		
    		ResponseEntity<String> response  = oAuth2RestTemplate.postForEntity(StartProcessDefinitionUrl, request, String.class);
    		JSONObject json = new JSONObject(response.getBody());
    		processInstanceId = (String) json.get("id");
    		OrbeonMetaData metadata;
    		if(omd != null) {
    			omd.setCamunda_id(processInstanceId);
    			orbeonMetaDataRepository.save(omd);
    		}
    		

    	}
    	catch(RestTemplateException rte) {
    		return rte.getError();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return "Exception during starting process definition instance.";
    	}
    	
    	return "OK";
    }
  
}
