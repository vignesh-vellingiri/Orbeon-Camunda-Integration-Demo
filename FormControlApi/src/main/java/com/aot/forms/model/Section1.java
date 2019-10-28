package com.aot.forms.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "Section-1")
public class Section1{
	
	public String getControl1() {
		return control1;
	}
	public void setControl1(String control1) {
		this.control1 = control1;
	}
	
	public String getControl2() {
		return control2;
	}
	public void setControl2(String control2) {
		this.control2 = control2;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getControl4() {
		return control4;
	}
	public void setControl4(String control4) {
		this.control4 = control4;
	}

	@JsonProperty(value = "control-1")
	private String control1;
	
	@JsonProperty(value = "control-2")
	private String control2;
	@JsonProperty(value = "groupName")
	private String groupName;
	@JsonProperty(value = "control-4")
	private String control4;
	@JsonProperty(value = "control-5")
	private String control5;
	@JsonProperty(value = "processDefinitionKey")
	private String  processDefinitionKey;
	@JsonProperty(value = "userName")
	private String  userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getControl5() {
		return control5;
	}
	public void setControl5(String control5) {
		this.control5 = control5;
	}
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public Section1() {}
	public Section1(String control1, String control2, String groupName, String control4, String control5, String processDefinitionKey, String userName) {
		this.control1 = control1;
		this.control2 =  control2;
		this.groupName = groupName;
		this.control4 = control4;
		this.control5 = control5;
		this.processDefinitionKey = processDefinitionKey;
		this.userName = userName;
		
	}
	
}