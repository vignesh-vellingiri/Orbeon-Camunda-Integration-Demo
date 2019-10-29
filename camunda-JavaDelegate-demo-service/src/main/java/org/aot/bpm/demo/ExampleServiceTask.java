package org.aot.bpm.demo;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExampleServiceTask implements JavaDelegate {

  public final static Logger LOGGER = Logger.getLogger(ExampleServiceTask.class.getName());

  /**
   * this method will be invoked by the process engine when the service task is executed.
   */
  public void execute(DelegateExecution execution) throws Exception {

    // use camunda model API to get current BPMN element
    String processInstanceId = execution.getProcessInstanceId();
    try {

		URL url = new URL("http://localhost:9090/api/v1/forms/unsecure/action?actionId=END_INSTANCE&processInstanceId=" + processInstanceId);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/xml");
		
		String input = "<section-1></section-1>";
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();
		
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
    
    // log status
    LOGGER.info("\n\n\nService Task is completed for " + execution.getProcessInstanceId() +"!\n\n\n");

  }

}