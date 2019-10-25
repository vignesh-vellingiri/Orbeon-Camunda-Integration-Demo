package com.aot.forms.model;

public class CamundaTaskResp {
	 private String id;
	 private String name;
	 private String assignee;
	 private String created;
	 private String due;
	 private String followUp;
	 private String delegationState;
	 private String description;
	 private String executionId;
	 private String owner;
	 private String parentTaskId;
	 private String priority;
	 private String processDefinitionId;
	 private String processInstanceId;
	 private String taskDefinitionKey;
	 private String caseExecutionId;
	 private String caseInstanceId;
	 private String caseDefinitionId;
	 private String suspended;
	 private String formKey;
	 private String tenantId;
	 
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getFollowUp() {
		return followUp;
	}
	public void setFollowUp(String followUp) {
		this.followUp = followUp;
	}
	public String getDelegationState() {
		return delegationState;
	}
	public void setDelegationState(String delegationState) {
		this.delegationState = delegationState;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public String getCaseExecutionId() {
		return caseExecutionId;
	}
	public void setCaseExecutionId(String caseExecutionId) {
		this.caseExecutionId = caseExecutionId;
	}
	public String getCaseInstanceId() {
		return caseInstanceId;
	}
	public void setCaseInstanceId(String caseInstanceId) {
		this.caseInstanceId = caseInstanceId;
	}
	public String getCaseDefinitionId() {
		return caseDefinitionId;
	}
	public void setCaseDefinitionId(String caseDefinitionId) {
		this.caseDefinitionId = caseDefinitionId;
	}
	public String getSuspended() {
		return suspended;
	}
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Task [id=%s, name=%s, assignee=%s, created=%s, due=%s, followUp=%s, delegationState=%s, description=%s, executionId=%s, owner=%s, parentTaskId=%s, priority=%s, processDefinitionId=%s, processInstanceId=%s, taskDefinitionKey=%s, caseExecutionId=%s, caseInstanceId=%s, caseDefinitionId=%s, suspended=%s, formKey=%s, tenantId=%s]",
				id,name,assignee,created,due,followUp,delegationState,description,executionId,owner,parentTaskId,priority,processDefinitionId,processInstanceId,taskDefinitionKey,caseExecutionId,caseInstanceId,caseDefinitionId,suspended,formKey,tenantId);
	}
}
