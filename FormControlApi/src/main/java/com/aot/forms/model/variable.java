package com.aot.forms.model;

import javax.xml.bind.annotation.XmlElement;

public class variable {
	String key;
	String value;
	
	public variable() {
	}
	
	public variable(String key, String value) {
		this.key = key;
		this.value =value;
	}
	@XmlElement
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Record [key=%s, value=%s]",key ,value);
	}
}
