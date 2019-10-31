package com.aot.codelabs.jsfwithoauth.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.aot.codelabs.jsfwithoauth.model.WorkflowTask;
import com.aot.codelabs.jsfwithoauth.service.TaskService;
import com.aot.codelabs.jsfwithoauth.service.TaskServiceImpl;

@ManagedBean(name = "viewTasks", eager = true)
@RequestScoped
public class ViewTasks {

  private WorkflowTask selectedTask;
  private List<WorkflowTask> tasks;
  
  public WorkflowTask getSelectedTask() {
	System.out.println(" ------------ Get selected task.");
	if(this.selectedTask != null)
		 this.selectedTask = taskService.getTask(selectedTask.getId());
	return selectedTask;
}
  

public void setSelectedTask(WorkflowTask selectedTask) {
	this.selectedTask = selectedTask;
}

private TaskService taskService = null;

  @PostConstruct
  private void init() {
    this.taskService = new TaskServiceImpl();
  }

  
  public List<WorkflowTask> getTasks(){
	  if(this.tasks == null)
		 this.tasks = taskService.getTasks();
	  return tasks;
	  
  }

}