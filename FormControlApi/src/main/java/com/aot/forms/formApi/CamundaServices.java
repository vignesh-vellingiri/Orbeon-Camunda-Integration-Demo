package com.aot.forms.formApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class CamundaServices {
	@Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    public String getTasks() {
      return oAuth2RestTemplate.getForObject("http://localhost:9092/camunda/engine-rest/task", String.class);
    }
  
}
