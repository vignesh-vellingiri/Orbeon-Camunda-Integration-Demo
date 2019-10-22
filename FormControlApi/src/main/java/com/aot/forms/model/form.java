package com.aot.forms.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "form")
public class form {
	
	@JsonProperty(value = "section-1")
	private Section1 section1;
	
	public Section1 getSection1() {
		return section1;
	}

	public void setSection1(Section1 section1) {
		this.section1 = section1;
	}

	public form() {}
	
	public form(String control1, String control2, String control3, String control4,  String control5) {
		this.section1 = new Section1(control1, control2, control3, control4,control5);
		
	}
	
	public form(String control5) {
		this.section1 = new Section1("","","","",control5);
		
	}
	
}
