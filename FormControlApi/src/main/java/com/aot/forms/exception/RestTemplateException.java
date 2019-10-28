package com.aot.forms.exception;

import org.springframework.http.HttpStatus;

public class RestTemplateException extends RuntimeException {

	  private DownStreamAPI api;
	  private HttpStatus statusCode;
	  private String error;

	  public RestTemplateException(DownStreamAPI api, HttpStatus statusCode, String error) {
	    super(error);
	    this.api = api;
	    this.statusCode = statusCode;
	    this.error = error;
	  }

	public DownStreamAPI getApi() {
		return api;
	}

	public void setApi(DownStreamAPI api) {
		this.api = api;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	  
	 
	}