package com.aot.forms.rest;

import java.io.IOException;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.aot.forms.exception.DownStreamAPI;
import com.aot.forms.exception.RestTemplateException;
import com.aot.forms.model.CamundaErrorResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestTemplateResponseErrorHandler  implements ResponseErrorHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) 
      throws IOException {
 
        return (
          httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR 
          || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }
 
    @Override
    public void handleError(ClientHttpResponse httpResponse) 
      throws IOException {
 
//        if (httpResponse.getStatusCode()
//          .series() == HttpStatus.Series.SERVER_ERROR) 
        {
        	log.debug("Camunda API RestTemplateResponseErrorHandler SERVER_ERROR ");
        	log.debug("Camunda API RestTemplateResponseErrorHandler Status Code :" +httpResponse.getStatusCode());
    		
        	ObjectMapper mapper = new ObjectMapper();
    		String testJson = IOUtils.toString(httpResponse.getBody(), Charsets.ISO_8859_1); 
    	    CamundaErrorResp errorResp = mapper.readValue(testJson, CamundaErrorResp.class);
    	    log.debug("Camunda API RestTemplateResponseErrorHandler response body" + errorResp);   
    		
    		throw new RestTemplateException(DownStreamAPI.CAMUNDA_API, httpResponse.getStatusCode(), errorResp.toString());
    	    //throw new IOException();
            
        } 
//        else if (httpResponse.getStatusCode()
//          .series() == HttpStatus.Series.CLIENT_ERROR) {
//        	log.debug("Camunda API RestTemplateResponseErrorHandler CLIENT_ERROR  ");
//        	// handle CLIENT_ERROR
//            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
//                throw new IOException();
//            }
//        }
    }
}