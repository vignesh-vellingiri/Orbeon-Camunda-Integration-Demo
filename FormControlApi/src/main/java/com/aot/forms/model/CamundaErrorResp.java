package com.aot.forms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CamundaErrorResp {
	
	
	@JsonProperty(value = "type")
	private String type;
	@JsonProperty(value = "message")
	private String message;
	
	
//	public CamundaErrorResp() {
//		
//	}
//	
//	public CamundaErrorResp(String type, String message) {
//		this.type = type;
//		this.message = message;
//	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.type = message;
	}
	
	@Override
	public String toString() {
		return String.format(
				"{type=%s, message=%s}", type, message);
	}
}
