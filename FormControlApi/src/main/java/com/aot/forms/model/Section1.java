package com.aot.forms.model;

import javax.xml.bind.annotation.XmlElement;
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
	public String getControl3() {
		return control3;
	}
	public void setControl3(String control3) {
		this.control3 = control3;
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
	@JsonProperty(value = "control-3")
	private String control3;
	@JsonProperty(value = "control-4")
	private String control4;
	@JsonProperty(value = "control-5")
	private String control5;
	
	public Section1() {}
	public Section1(String control1, String control2, String control3, String control4, String control5) {
		this.control1 = control1;
		this.control2 =  control2;
		this.control3 = control3;
		this.control4 = control4;
		this.control5 = control5;
	}
	
}