package com.aot.forms.formApi;

import java.io.File;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import com.aot.forms.model.Record;
import com.aot.forms.model.RecordXml;
import com.aot.forms.model.form;
import java.util.UUID;

@RestController
@Configuration
//@ComponentScan(basePackages = "com.aot.forms.model.*")
public class FormsController {
	
	@Autowired
	private OrbeonMetaDataRepository orbeonMetaDataRepository;
	
    @RequestMapping("/healthCheck")
    public String greeting() {
        return "OK";
    }
    
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