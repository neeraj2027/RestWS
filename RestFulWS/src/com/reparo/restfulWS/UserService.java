package com.reparo.restfulWS;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/UserService")
public class UserService {
	/*
	 * UserDao userDao = new UserDao();
	 * 
	 * @GET
	 * 
	 * @Path("/users")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public List<User> getUsers(){ return
	 * userDao.getAllUsers(); }
	 */

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser() {

		User user = new User();
		user.setId(1);
		user.setName("b");
		user.setProfession("c");
		// String result = "User created : " + user;
		// return Response.status(200).entity(result).build();
		return user;
	}

	@POST
	@Path("/post")
	public Response createProductInJSON() {

		User user = new User();
		user.setId(1);
		user.setName("b");
		user.setProfession("c");
		String result = "User created : " + user;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/postJSON")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createProduct(User user) {
		String result = "User created : " + user;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/postXML")
	@Consumes(MediaType.APPLICATION_XML)
	public Response consumeXML(User user) {
		String result = "User created : " + user;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/msg")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response message(String data) {
		String result = "Data post: " + data;
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/{varX}/{varY}/{varZ}")
	@Produces({ "application/xml", "application/json" })
	public User createUser(@PathParam("varX") int varX, @PathParam("varY") String varY,
			@PathParam("varY") String varZ) {
		User todo = new User();
		todo.setId(varX);
		todo.setName(varY);
		todo.setProfession(varZ);
		return todo;
	}

	@POST
	@Path("post/{varX}/{varY}/{varZ}")
	@Produces({ "application/xml", "application/json" })
	public User createUserPost(@PathParam("varX") int varX, @PathParam("varY") String varY,
			@PathParam("varY") String varZ) {
		User todo = new User();
		todo.setId(varX);
		todo.setName(varY);
		todo.setProfession(varZ);
		return todo;
	}

	@POST
	@Path("/form")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createUserForm(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("profession") String profession) {

		System.out.println("creating course");
		System.out.println("name: " + name);
		System.out.println("id: " + id);
		System.out.println("profession: " + profession);

	}
	// ------------------------------------------------------------------------------------------------------------------------

	// ----------------------------PARAMETERS
	// USE--------------------------------------------------------------------------------------------
	// URL:=
	// http://localhost:9090/RestFulWS/rest/UserService/cars/BMW/320;color=black
	@GET
	@Path("/cars/{brand}/{model}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCars(@PathParam("brand") String brand, @PathParam("model") PathSegment car) {

		String model = car.getPath();
		String color = car.getMatrixParameters().getFirst("color");

		System.out.println("brand: " + brand);
		System.out.println("model: " + model);
		System.out.println("color: " + color);

		return Response.ok().build();
	}

	// http://localhost:9090/RestFulWS/rest/UserService/cars/BMW/E92;color=black/M3;maker=john/2015
	@GET
	@Path("/cars/{brand}/{model: .+}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCars(@PathParam("brand") String brand, @PathParam("model") List<PathSegment> cars,
			@PathParam("year") Integer year) {

		System.out.println("brand: " + brand);
		for (PathSegment segment : cars) {
			System.out.println("model: " + segment.getPath());
			for (String name : segment.getMatrixParameters().keySet()) {
				String value = segment.getMatrixParameters().getFirst(name);
				System.out.println("\tmatrix param name: " + name + " value: " + value);
			}
		}
		System.out.println("year: " + year);

		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrders(@Context UriInfo info) {

		PathSegment model = info.getPathSegments().get(1);
		System.out.println("id: " + model.getPath());
		for (String name : model.getMatrixParameters().keySet()) {
			String value = model.getMatrixParameters().getFirst(name);
			System.out.println("\tmatrix param name: " + name + " value: " + value);
		}

		return Response.ok().build();
	}

	// -----------------------------------------HTTP Header
	// parameters-------------------------------------------------------------------------------
	// http://localhost:9090/RestFulWS/rest/UserService/http
	@GET
	@Path("/http")
	public Response getProducts(@Context HttpServletRequest request) {

		String userAgent = request.getHeader("user-agent");

		return Response.ok().entity("getProducts() user-agent: " + userAgent).build();
	}

	// http://localhost:9090/RestFulWS/rest/UserService/httph
	@GET
	@Path("/httph")
	public Response getUser(@Context HttpHeaders headers) {

		String userAgent = headers.getRequestHeader("user-agent").get(0);

		return Response.ok().entity("getUser() user-agent: " + userAgent).build();
	}

	@GET
	@Path("/httpuse")
	public Response getOrders(@HeaderParam("user-agent") String userAgent) {

		return Response.ok().entity("getOrders() user-agent: " + userAgent).build();
	}

	// -----------------------------------------HTTP Header parameters
	// End-------------------------------------------------------------------------------

	// -----------------------------------------Bean parameters
	// Start-------------------------------------------------------------------------------
	
	
	@POST
	@Path("/beans")
    public void createCustomer(@BeanParam UserInput user){
        System.out.println(user);
    }
	

	// -----------------------------------------Bean parameters
	// End-------------------------------------------------------------------------------

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addEmployee(User e) throws URISyntaxException {
		if (e == null) {
			return Response.status(400).entity("Please add employee details !!").build();
		}

		if (e.getName() == null) {
			return Response.status(400).entity("Please provide the employee name !!").build();
		}

		return Response.created(new URI("/rest/employees/" + e.getId())).build();
	}

	/*
	 * @GET
	 * 
	 * @Path("/users")
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public Response getUsersJson() throws
	 * JSONException{ User user = new User(); user.setId(2); user.setName("Lokesh");
	 * user.setProfession("Gupta"); return
	 * Response.status(200).entity(user).build();
	 * 
	 * }
	 */
}