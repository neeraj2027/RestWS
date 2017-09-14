package com.reparo.post.restfulWS.client.params;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

@SuppressWarnings("deprecation")
public class JersyClientParamPost {
	
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", 1);
		map.put("name", "neeraj");
		map.put("profession", "analyst");
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:9090/RestFulWS/rest/UserService/{id}/{name}/{profession}").resolveTemplates(map);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	
	}
	

}
