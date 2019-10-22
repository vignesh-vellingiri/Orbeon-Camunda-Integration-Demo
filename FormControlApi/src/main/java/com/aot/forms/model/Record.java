package com.aot.forms.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Record {
	private String recordId;
	private String applicationType;
	private String formUrl;
	private String status;
	
	public Record(String recordId,String applicationType, String formUrl,String status) {
		this.recordId = recordId;
		this.applicationType = applicationType;
		this.formUrl = formUrl;
		this.status = status;
	}
	
	public Record() {
		
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getFormUrl() {
		return formUrl;
	}
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Record [recordId=%s, applicationType=%s, formUrl=%s, status=%s]", recordId, applicationType,
				formUrl, status);
	}
}
