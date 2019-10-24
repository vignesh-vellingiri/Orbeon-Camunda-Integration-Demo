package com.aot.forms.rest;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aot.forms.formApi.CamundaServices;
import com.aot.forms.formApi.OrbeonMetaData;
import com.aot.forms.formApi.OrbeonMetaDataRepository;
import com.aot.forms.model.RecordXml;
import com.aot.forms.model.form;


@RestController
@RequestMapping("/api/v1/forms")
public class FormsController {
	
	@Autowired
	private OrbeonMetaDataRepository orbeonMetaDataRepository;
	
	@Autowired
	private CamundaServices camundaServices;
	
	
	@GetMapping(path = "/healthCheck")
    public String greeting() {
        return "OK";
    }
	
	@GetMapping(path = "/secure/healthCheck" )
    @PreAuthorize("hasAnyAuthority('ROLE_role0')")
    public String greeting1() {
    	try {
    		
    		String resp = camundaServices.getTasks();
    		System.out.println("-------- Tasks :  " + resp);
    		return resp;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        return "OK";
    }

	/*
	 * @PreAuthorize("hasAnyAuthority('ROLE_USER')") public
	 * ResponseEntity<Set<String>> getAuthorizedUserRoles() { return
	 * ResponseEntity.ok(SecurityContextUtils.getUserRoles()); }
	 */
    
    @RequestMapping(value = "/records", 
    		method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_XML_VALUE)
    public form getRecord(@RequestParam String name) throws Exception {
    	UUID uuid = UUID.randomUUID();
    	String randomUUIDString = uuid.toString();
        form frm = new form(randomUUIDString);
        System.out.println(randomUUIDString);
        return frm;
    }
    
    @PostMapping(value = "/metadata")
    public String updateMetadata(@RequestBody  RecordXml RecordXml) {
    	System.out.println("appType:");
        System.out.println("appType:" + RecordXml );
        return "Ok";
    }
    
    @PostMapping(value = "/dummy")
     public String createDummy(@RequestBody  Map<String, Object> RecordXml, @RequestParam Map<String, String> reqParam) {
    //public String createDummy(@RequestBody  OrbeonMetaData RecordXml, @RequestParam Map<String, String> reqParam) {
    	reqParam.forEach((key, value) -> System.out.println(key + ":" + value));
    	
    	OrbeonMetaData omd = new OrbeonMetaData(reqParam.get("document"),reqParam.get("app"),reqParam.get("docid"));
        //System.out.println("metadata:" + omd );
        orbeonMetaDataRepository.save(omd);
        return "Ok";
    }
    
	
    
}