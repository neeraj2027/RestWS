package com.reparo.restfulWS;  

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/UserService")
public class UserService {  
/*   UserDao userDao = new UserDao();  
   
   @GET 
   @Path("/users") 
   @Produces(MediaType.APPLICATION_XML) 
   public List<User> getUsers(){ 
      return userDao.getAllUsers(); 
   }  */

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_XML) 
	public User getUser() {

		User user = new User();
		user.setId(1);
		user.setName("b");
		user.setProfession("c");
		//String result = "User created : " + user;
		//return Response.status(200).entity(result).build();
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
   	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
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
        String result = "Data post: "+data;
        return Response.status(201).entity(result).build(); 
    }
    
//------------------------------------------------------------------------------------------------------------------------
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
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addEmployee( User e ) throws URISyntaxException
    {
        if(e == null){
            return Response.status(400).entity("Please add employee details !!").build();
        }
         
        if(e.getName() == null) {
            return Response.status(400).entity("Please provide the employee name !!").build();
        }
         
        return Response.created(new URI("/rest/employees/"+e.getId())).build();
    }
   
  /* @GET 
   @Path("/users") 
   @Produces(MediaType.APPLICATION_XML) 
   public Response getUsersJson() throws JSONException{ 
	   User user = new User();
       user.setId(2);
       user.setName("Lokesh");
       user.setProfession("Gupta");
       return Response.status(200).entity(user).build();
      
   }  */
}