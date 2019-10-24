package com.aot.forms.security;

/*import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;*/
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;

@Component
public class KeycloakExtension {


	/*
	 * protected KeycloakSecurityContext getKeycloakSecurityContext() {
	 * 
	 * ServletRequestAttributes attributes = (ServletRequestAttributes)
	 * RequestContextHolder.getRequestAttributes(); Principal principal =
	 * attributes.getRequest().getUserPrincipal();
	 * 
	 * if (principal instanceof KeycloakPrincipal) {
	 * //System.out.println("----------- keycloak contecxt : " +
	 * KeycloakPrincipal.class.cast(principal).getKeycloakSecurityContext().getToken
	 * ().getOtherClaims().get("group")); //KeycloakSecurityContext koj =
	 * KeycloakPrincipal.class.cast(principal).getKeycloakSecurityContext(); return
	 * KeycloakPrincipal.class.cast(principal).getKeycloakSecurityContext(); }
	 * 
	 * return null; }
	 * 
	 * public String getUserGroup() { return
	 * getKeycloakSecurityContext().getToken().getOtherClaims().get("group").
	 * toString(); } public String getUserName() { return
	 * getKeycloakSecurityContext().getToken().getName(); }
	 */
}

