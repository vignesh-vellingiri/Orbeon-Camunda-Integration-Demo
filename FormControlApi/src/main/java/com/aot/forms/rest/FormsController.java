package com.aot.forms.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aot.forms.config.SecurityContextUtils;
import com.aot.forms.formApi.CamundaServices;
import com.aot.forms.formApi.OrbeonMetaData;
import com.aot.forms.formApi.OrbeonMetaDataRepository;
import com.aot.forms.model.CamundaTaskResp;
import com.aot.forms.model.FormControlAction;
import com.aot.forms.model.RecordXml;
import com.aot.forms.model.form;
import com.aot.forms.model.variable;


@RestController
@RequestMapping("/api/v1/forms")
public class FormsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FormsController.class);
	
	@Autowired
	private OrbeonMetaDataRepository orbeonMetaDataRepository;
	
	@Autowired
	private CamundaServices camundaServices;
	
	@Autowired
	private SecurityContextUtils securityContextUtils;
	
	@GetMapping(path = "/healthCheck")
    public String greeting() {
        return "OK";
    }
	
	@GetMapping(path = "/camunda/tasks" , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_role0')")
    public CamundaTaskResp[] getCamundaTasks() {
		CamundaTaskResp[] resp = null;
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");  
	    
    	try {
    		
    		List<String> groupList = securityContextUtils.getGroups();
    		resp = camundaServices.getTasks(groupList);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	if(resp != null) 
    		return resp;
        else 
        	return new CamundaTaskResp[0];
       
    }
	
	@GetMapping(path = "/camunda/tasks/claim" , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_role0')")
    public CamundaTaskResp[] claimCamundaTasks() {
		CamundaTaskResp[] resp = null;
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");  
	    
    	try {
    		
    		List<String> groupList = securityContextUtils.getGroups();
    		resp = camundaServices.getTasks(groupList);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	if(resp != null) 
    		return resp;
        else 
        	return new CamundaTaskResp[0];
       
    }

	@PostMapping(value = "/action")
	@PreAuthorize("hasAnyAuthority('ROLE_role0')")
    public ResponseEntity<String> action(@RequestBody  Map<String, Object> RecordXml, @RequestParam Map<String, String> reqParam) {
		reqParam.forEach((key, value) -> LOGGER.debug(key + ":" + value));
		
		String actionId = reqParam.get("actionId");
		if(actionId.equals("START_INSTANCE")) {
			OrbeonMetaData omd = new OrbeonMetaData(reqParam.get("document"),reqParam.get("app"),reqParam.get("docid"));
			orbeonMetaDataRepository.save(omd);
			camundaServices.startProcessDefinition(null,null);
			return new ResponseEntity<>("SAVE_METADATA", HttpStatus.OK);
			//return "SAVE_METADATA OK";
		}
		else if(actionId.equals("CLAIM_TASK")) {
			String taskId = reqParam.get("taskId");
			LOGGER.debug("Claim request for taskId : " + taskId + " and user : " + securityContextUtils.getUserName());
			String result =  camundaServices.claimTasks(reqParam.get("taskId"),securityContextUtils.getUserName());
			if(!result.equals("OK")) {
				LOGGER.debug("Claim request Not Successful for taskId : " + taskId + " and user : " + securityContextUtils.getUserName());
				return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
				//return "CLAIM_TASK ERROR";
			}
			else {
				LOGGER.debug("Claim request Successful for taskId : " + taskId + " and user : " + securityContextUtils.getUserName());
				return new ResponseEntity<>("CLAIM_TASK OK", HttpStatus.INTERNAL_SERVER_ERROR);
				//return "CLAIM_TASK OK";
			}
		}
		
		return new ResponseEntity<>("OK", HttpStatus.OK);
		//return "Ok";
   }
	
	
    @RequestMapping(value = "/records", 
    		method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_XML_VALUE)
    public form getRecord(@RequestParam String name) throws Exception {
    	UUID uuid = UUID.randomUUID();
    	String randomUUIDString = uuid.toString();
        form frm = new form(randomUUIDString);
        LOGGER.debug("Request to load initial Orbeon form data. New random id: " + randomUUIDString);
        return frm;
    }
    
    
    
    /*
     * SAVE_METADATA - Save metadata saves the metadata and creates a process instance in Camunda.
     * 
     */
    @PostMapping(value = "/unsecure/action", consumes = MediaType.APPLICATION_XML_VALUE)
     public ResponseEntity<String> createDummy(@RequestBody  FormControlAction formControlAction, @RequestParam Map<String, String> reqParam) {
    	reqParam.forEach((key, value) -> System.out.println(key + ":" + value));
    	HashMap<String, String> varMap = new HashMap<String, String>();
    	
    	for(variable var: formControlAction.getVariables()) {
    		varMap.put(var.getKey(), var.getValue());
    	}
    	
		String actionId = reqParam.get("actionId");
		String processDefinitionKey = reqParam.get("processDefinitionKey");
    	if(actionId.equals("START_INSTANCE")) {
			OrbeonMetaData omd = new OrbeonMetaData(reqParam.get("document"),reqParam.get("app"),reqParam.get("docid"));
			orbeonMetaDataRepository.save(omd);
			//camundaServices.startProcessDefinition(varMap,processDefinitionKey);
			return new ResponseEntity<>("SAVE_METADATA", HttpStatus.OK);
			
		}
    	
    	return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
	
    
}