package org.aot.bpm.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class dummy {

	public static void main(String args[]) {
		
		try {
		URL url = new URL("http://localhost:9090/api/v1/forms/unsecure/action?actionId=END_INSTANCE&processInstanceId=" + "15209910-fa7b-11e9-956e-3cf0114a5415");
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
	}
}
