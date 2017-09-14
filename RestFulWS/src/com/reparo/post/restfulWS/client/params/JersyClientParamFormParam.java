package com.reparo.post.restfulWS.client.params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

@SuppressWarnings("deprecation")
public class JersyClientParamFormParam {
	
	public static void main(String[] args) {
		
		Form form = new Form();
		form.param("id", "10");
		form.param("name", "Neeraj");
		form.param("profession", "Analyst");
		
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:9090/RestFulWS/rest/UserService/form");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.form(form));
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	
	}
	

}
