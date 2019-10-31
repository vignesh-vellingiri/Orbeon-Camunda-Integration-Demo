package com.aot.codelabs.jsfwithoauth.service;

import java.util.List;

import com.aot.codelabs.jsfwithoauth.model.WorkflowTask;

public interface TaskService {
	
	List<WorkflowTask> getTasks();

	WorkflowTask getTask(String taskId);
	
	String createTask(WorkflowTask task);
	
	WorkflowTask updateTask(WorkflowTask task);
	
	boolean deleteTask(String taskId);
	
	WorkflowTask claimTask(WorkflowTask task);
	
	WorkflowTask completeTask(WorkflowTask task);
	
	WorkflowTask revertTaskToUser(WorkflowTask task);
	

}
