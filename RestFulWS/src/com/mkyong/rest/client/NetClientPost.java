package com.mkyong.rest.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.entity.StringEntity;

import com.reparo.restfulWS.User;

public class NetClientPost {

	// http://localhost:8080/RESTfulExample/json/product/post
	public static void main(String[] args) {

		try {

			URL url = new URL("http://localhost:9090/RestFulWS/sample/json/product/post");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			StringEntity input1 = new StringEntity("{\"qty\":100,\"name\":\"iPad 4\"}");

			String input = "{\"id\":100,\"name\":\"neeraj\",\"profession\":\"engineer\"}";
			
			String input2 = "{\"qty\":100,\"name\":\"iPad 4\"}";

			User user = new User(1, "neeraj", "hello");

			OutputStream os = conn.getOutputStream();
			/*BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write(input2);
            writer.flush();
            writer.close();*/
			
			os.write(input2.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

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