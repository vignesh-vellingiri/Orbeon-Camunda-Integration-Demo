package com.aot.codelabs.jsfwithoauth.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.aot.codelabs.jsfwithoauth.model.WorkflowTask;

@ManagedBean(name = "updateTask", eager = true)
@ViewScoped
public class UpdateTask {

    private WorkflowTask task;

	public WorkflowTask getTask() {
		return task;
	}

	public void setTask(WorkflowTask task) {
		this.task = task;
	}
	
	public void update() {
		System.out.println("Updated task details."+this.task.getAssignee());
	}

    
}