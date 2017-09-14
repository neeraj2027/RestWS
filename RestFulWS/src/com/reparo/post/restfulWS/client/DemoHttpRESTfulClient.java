package com.reparo.post.restfulWS.client;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.reparo.restfulWS.User;

public class DemoHttpRESTfulClient
{
	//Create a new instance of http client which will connect to REST api over network
	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	
	public static void main(String[] args) throws Exception 
	{
		//Demo Get request
	//	demoGetRESTAPI();
		
		//Demo Post request
		demoPostRESTAPI();
	}
	
	public static void demoGetRESTAPI() throws Exception 
	{
		try
		{
			//Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
			//Choice depends on type of method you will be invoking.
			HttpGet getRequest = new HttpGet("http://localhost:8080/RESTfulDemoApplication/user-management/users/10");
			
			//Set the API media type in http accept header
			getRequest.addHeader("accept", "application/xml");
			 
			//Send the request; It will immediately return the response in HttpResponse object
			HttpResponse response = httpClient.execute(getRequest);
			
			//verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) 
			{
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}
			
			//Now pull back the response object
			HttpEntity httpEntity = response.getEntity();
			String apiOutput = EntityUtils.toString(httpEntity);
			
			//Lets see what we got from API
			System.out.println(apiOutput); //<user id="10"><firstName>demo</firstName><lastName>user</lastName></user>
			
			//In realtime programming, you will need to convert this http response to some java object to re-use it.
			//Lets see how to jaxb unmarshal the api response content
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	        User user = (User) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
	        
	        //Verify the populated object
	        System.out.println(user.getId());
	        System.out.println(user.getName());
	        System.out.println(user.getProfession());
		}
		finally
		{
			//Important: Close the connect
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	public static void demoPostRESTAPI() throws Exception 
	{
		User user = new User();
		user.setId(100);
		user.setName("Lokesh");
		user.setProfession("Gupta");
		
		StringWriter writer = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(user, writer);
        
		try
		{
			//Define a postRequest request
			HttpPost postRequest = new HttpPost("http://localhost:9090/RestFulWS/rest/UserService/postJSON");
			
			//Set the API media type in http content-type header
			postRequest.addHeader("content-type", "application/xml");
			
			//Set the request post body
			StringEntity userEntity = new StringEntity(writer.getBuffer().toString());
			postRequest.setEntity(userEntity);
			 
			//Send the request; It will immediately return the response in HttpResponse object if any
			HttpResponse response = httpClient.execute(postRequest);
			
			//verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 201 &&  false) 
			{
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}
		}
		finally
		{
			//Important: Close the connect
			httpClient.getConnectionManager().shutdown();
		}
	}
}
