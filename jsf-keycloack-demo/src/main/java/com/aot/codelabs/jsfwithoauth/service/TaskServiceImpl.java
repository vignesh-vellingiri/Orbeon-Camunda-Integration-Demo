package com.aot.codelabs.jsfwithoauth.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.ws.rs.client.WebTarget;

import com.aot.codelabs.jsfwithoauth.model.WorkflowTask;  

public class TaskServiceImpl implements TaskService {
	
	
	public Client getOauthClient() {
		FacesContext context = FacesContext.getCurrentInstance();	
		HttpServletRequest httpsr = (HttpServletRequest) context.getExternalContext().getRequest();
		RefreshableKeycloakSecurityContext cc =  (RefreshableKeycloakSecurityContext) (httpsr).getAttribute(KeycloakSecurityContext.class.getName());	
		String bearerToken =cc.getTokenString(); // cc.getTokenString()
		//System.out.println("------------------------ " +  bearerToken);
		Client client =   ClientBuilder.newClient().register(OAuth2ClientSupport.feature(bearerToken));
		return client;
	}
	
public List<WorkflowTask> getTasks() {
		
		Client client = getOauthClient();
		// Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:9090").path("/api/v1/forms/camunda/tasks");

	    Builder builder =   target.request();
	    
	 //  List<WorkflowTask> taskList = (List<WorkflowTask>) builder.get(WorkflowTask.class);
	    Response response = builder.get();
	    WorkflowTask[] taskList =  response.readEntity(WorkflowTask[].class);
	    System.out.println("------------------------ getTasks " +  taskList.length);
	    List<WorkflowTask> tl = Arrays.asList(taskList);
		return tl;
	}
	
	public WorkflowTask claimTask(WorkflowTask task) {
		try {
			Client client = getOauthClient();
			System.out.println("------------------------ claimTask " );
			WebTarget target = client.target("http://localhost:9090").path("/api/v1/forms/action").queryParam("actionId", "CLAIM_TASK").queryParam("taskId", task.getId());
			Invocation.Builder invocationBuilder  = target.request();
			String emptyXml = "<root/>";
			String taskIdReq = task.getId();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			 builder = factory.newDocumentBuilder();
			Document doc =builder.parse(new InputSource(new StringReader(emptyXml)));
			
		    System.out.println(" ---------------------- " + target.toString() + " -------------- "  );
//		    String resp = invocationBuilder.post(Entity.entity(doc, MediaType.APPLICATION_XML), String.class);
		    String resp = invocationBuilder.post(Entity.xml(emptyXml), String.class);
		    System.out.println(" ---------------------- " + target.toString() + " -------------- " + resp.toString() );
		    task = getTask(taskIdReq);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return task;
	}
	
	/**
	 * Get a task by Id
	 */
	public WorkflowTask getTask(String id) {
		
//		Client client = ClientBuilder.newClient();
		System.out.println("------------------------ getTask Details " );
		Client client = getOauthClient();
		WebTarget target = client.target("http://localhost:9090").path("/api/v1/forms/camunda/tasks/"+id);

	    Builder builder =   target.request();

	    WorkflowTask task = builder.get(WorkflowTask.class);
		
		return task;
	}
	
	

	public String createTask(WorkflowTask task) {
		// TODO Auto-generated method stub
		return null;
	}

	public WorkflowTask updateTask(WorkflowTask task) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteTask(String taskId) {
		// TODO Auto-generated method stub
		return false;
	}

}
