package com.reparo.post.restfulWS.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.reparo.restfulWS.User;

public class NetClientPost {

	// http://localhost:8080/RESTfulExample/json/product/post
	public static void main(String[] args) {

		callPostMethod();
		
		callGetMethod();
		
	}

	private static void callGetMethod() {
		URL url;
		try {
			url = new URL("http://localhost:9090/RestFulWS/rest/UserService/user");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/xml");
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String apiOutput = br.readLine();
			//System.out.println(apiOutput);
			conn.disconnect();
			
			User dd = JAXB.unmarshal(new StringReader(apiOutput), User.class);
			
			/*JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			User users = (User) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));*/
			
			System.out.println(conn.getResponseCode());
			System.out.println(dd.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private static void callPostMethod() {
		try {

			URL url = new URL("http://localhost:9090/RestFulWS/rest/UserService/postJSON");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/xml");

			User user = new User(1, "neeraj", "hello");

			String xmlString = jaxbObjectToXML(user);

			OutputStream os = conn.getOutputStream();
			os.write(xmlString.getBytes());
			os.flush();
			/*
			 * if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) { throw new
			 * RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); }
			 */
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
	
/*	A convenient option is to use javax.xml.bind.JAXB:

		StringWriter sw = new StringWriter();
		JAXB.marshal(customer, sw);
		String xmlString = sw.toString();

		The reverse process (unmarshal) would be:

		Customer customer = JAXB.unmarshal(new StringReader(xmlString), Customer.class);*/
		
	private static String jaxbObjectToXML(User customer) {
		String xmlString = "";
		try {
			JAXBContext context = JAXBContext.newInstance(User.class);
			Marshaller m = context.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

			StringWriter sw = new StringWriter();
			m.marshal(customer, sw);
			xmlString = sw.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return xmlString;
	}
}