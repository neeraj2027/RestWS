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

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

@SuppressWarnings("deprecation")
public class JersyClientJSON {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		//client.register(GensonJsonConverter.class);
		WebTarget webTarget = client.target("http://localhost:9090/RestFulWS/rest/UserService/postJSON");

		//String input = "{\"message\":\"Hello\"}";
		String input ="{\"id\":100,\"name\":\"neeraj\",\"profession\":\"engineer\"}";
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(input, MediaType.APPLICATION_JSON));

		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));

	}

}
