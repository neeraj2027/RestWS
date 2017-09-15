package com.javapapers.webservices.rest.jersey;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.filter.LoggingFilter;

public class RESTfulJerseyClient {

	private static final String webServiceURI = "http://localhost:9090/RESTful_Service_Authentication";

	public static void main(String[] args) {
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		client.register(feature);
		URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
		WebTarget webTarget = client.target(serviceURI);
		

//		System.out.println(webTarget.path("rest").path("helloworld").request()
//				.accept(MediaType.TEXT_PLAIN).post(null).toString());
		
		// html
		System.out.println(webTarget.path("rest").path("helloworld").request()
				.accept(MediaType.TEXT_HTML).post(null));
		
//		// response
//		System.out.println(webTarget.path("rest").path("helloworld").request()
//				.accept(MediaType.TEXT_PLAIN).get(Response.class).toString());
//
//		// text
//		System.out.println(webTarget.path("rest").path("helloworld").request()
//				.accept(MediaType.TEXT_PLAIN).get(String.class));
//
//		// xml
//		System.out.println(webTarget.path("rest").path("helloworld").request()
//				.accept(MediaType.TEXT_XML).get(String.class));

	}
}
