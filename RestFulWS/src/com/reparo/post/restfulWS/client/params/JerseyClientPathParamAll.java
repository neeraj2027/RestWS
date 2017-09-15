package com.reparo.post.restfulWS.client.params;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

@SuppressWarnings("deprecation")
public class JerseyClientPathParamAll {

	public static void main(String[] args){
		//http://localhost:9090/RestFulWS/rest/UserService/cars/BMW/E92;color=black/M3;maker=john/2015
    	
    	Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        Response response = client
                .target("http://localhost:9090/RestFulWS/rest/UserService/cars/{brand}")
                .resolveTemplate("brand", "BMW")
                .path("E92").matrixParam("color", "black")
                .path("M3").matrixParam("maker", "john")
                .path("2015")
                .request()
                .get();
        
        if (response.getStatus() == 200){
            System.out.println("Response: " + response.readEntity(String.class));
        }
        client.close();
        
        Client client1 = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        Response response1 = client1
                .target("http://localhost:9090/RestFulWS/rest/UserService/{brand}")
                .resolveTemplate("brand", "4492")
                .matrixParam("color", "black")
                .matrixParam("maker", "john")
                .request()
                .get();


        if (response1.getStatus() == 200){
            System.out.println("Response: " + response1.readEntity(String.class));
        }
        client1.close();
    }
}