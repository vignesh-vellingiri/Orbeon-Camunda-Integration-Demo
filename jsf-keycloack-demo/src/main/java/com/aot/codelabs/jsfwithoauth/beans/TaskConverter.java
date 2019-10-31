package com.aot.codelabs.jsfwithoauth.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.aot.codelabs.jsfwithoauth.model.WorkflowTask;
import com.aot.codelabs.jsfwithoauth.service.TaskService;
import com.aot.codelabs.jsfwithoauth.service.TaskServiceImpl;

//@Named
@ManagedBean(name = "taskConverter", eager = true)
@RequestScoped
public class TaskConverter implements Converter {
	
	private TaskService taskService = null;

	  @PostConstruct
	  private void init() {
	    this.taskService = new TaskServiceImpl();
	  }

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value == null || value.isEmpty()) {
	            return null;
	        }

	        try {
	            return taskService.getTask(value);
	        } catch (NumberFormatException e) {
	            throw new ConverterException("The value is not a valid Task ID: " + value, e);
	        }
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
            return "";
        }

        if (value instanceof WorkflowTask) {
            String id = ((WorkflowTask) value).getId();
            return id;
        } else {
            throw new ConverterException("The value is not a valid Task instance: " + value);
        }
	}

}
