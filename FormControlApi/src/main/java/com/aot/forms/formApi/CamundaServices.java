package com.aot.forms.formApi;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.aot.forms.model.CamundaTaskResp;

@Component
@Import({CamundaTaskResp.class})
public class CamundaServices {
	@Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;
	
	
	private CamundaTaskResp[] camundaTaskReq;
	
	private final String TASK_URL = "http://localhost:9092/camunda/engine-rest/task";

    /**
     * @param groups
     * @return
     */
    public CamundaTaskResp[] getTasks(List<String> groups) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	JSONObject taskJsonObject = new JSONObject();
    	CamundaTaskResp[] taskResponse ;
    	
    	for (String group : groups) {
			try {
	    		taskJsonObject.put("candidateGroup", group.replaceAll("/", ""));
				HttpEntity<String> request = new HttpEntity<String>(taskJsonObject.toString(),headers);
				taskResponse = oAuth2RestTemplate.postForObject(TASK_URL, request, CamundaTaskResp[].class);
		    	if(taskResponse.length > 0)
		    		camundaTaskReq = ArrayUtils.addAll(camundaTaskReq,taskResponse);
		    	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
    	
//    	for (CamundaTaskResp ctr : camundaTaskReq)
//    		System.out.println("--------Response" + ctr);
    	
    	return camundaTaskReq;
    }
  
}
