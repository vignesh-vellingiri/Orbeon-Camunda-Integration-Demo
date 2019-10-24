package com.aot.forms.config;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public  class SecurityHelper {
	
	public static String getGroups() {
		try {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			OAuth2AuthenticationDetails oad = (OAuth2AuthenticationDetails) securityContext.getAuthentication().getDetails();
			ObjectMapper objectMapper = new ObjectMapper();
			
			Jwt jwt = JwtHelper.decode(oad.getTokenValue());
			Map<String, Object> claims = objectMapper.readValue(jwt.getClaims(), Map.class);
			return claims.get("group").toString();
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}
}
