package com.reparo.json.restfulWS.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PureJavaClient {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:9090/RestFulWS/rest/UserService/users");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String apiOutput = br.readLine();
			System.out.println(apiOutput);
			conn.disconnect();
			

			//JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			//Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			//Users users = (Users) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
			
	/*		for (User user : users.getEmployees()) {
				System.out.println(user.getId());
				System.out.println(user.getName());
				System.out.println(user.getProfession());
			}
*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
