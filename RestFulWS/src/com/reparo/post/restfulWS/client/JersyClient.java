package com.reparo.post.restfulWS.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import com.reparo.restfulWS.User;

@SuppressWarnings("deprecation")
public class JersyClient {
	
	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:9090/RestFulWS/rest/UserService/postJSON");
		
		//---------------------------------JSON-------------------------------//
		String input = "{\"id\":100,\"name\":\"neeraj\",\"profession\":\"engineer\"}";
		Invocation.Builder invocationBuilder1 = webTarget.request(MediaType.APPLICATION_JSON);
		Response response1 = invocationBuilder1.post(Entity.entity(input, MediaType.APPLICATION_JSON));
		System.out.println(response1.getStatus());
		System.out.println(response1.readEntity(String.class));
		//--------------------------------------------JSON----------------------------------------------//
		
		//---------------------------------XML-------------------------------//
		User user = new User(1, "neeraj", "hello");
		//String ss = "<user><id>100</id><name>neeraj</name><profession>engineer</profession></user>";
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_XML));
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
		//-------------------------------------------XML-----------------------------------------------//
	
	}
	

}
