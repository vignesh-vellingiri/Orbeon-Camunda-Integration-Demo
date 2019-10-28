package com.aot.forms.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormControlAction  implements Iterable<variable> {
	
	ArrayList<variable> variables = new ArrayList<variable>();

	@Override
    public Iterator<variable> iterator() {
        return variables.iterator();
    }
	
	public FormControlAction() {	
	}
	
	public FormControlAction(ArrayList<variable> variables) {	
		this.variables = variables;
	}
	
	@XmlElement
	public ArrayList<variable> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<variable> variables) {
		this.variables = variables;
	}

	
	
	@Override
	public String toString() {
		return String.format(
				"Record [record]");
	}
}

