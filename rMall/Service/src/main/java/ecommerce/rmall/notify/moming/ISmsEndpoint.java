package ecommerce.rmall.notify.moming;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@WebService
public interface ISmsEndpoint {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String getBalance(@QueryParam("action")String action, 
			@QueryParam("username")String username, 
			@QueryParam("password")String password);
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String sendMessage(@QueryParam("action")String action, 
			@QueryParam("username")String username, 
			@QueryParam("password")String password,
			@QueryParam("encode")String encode, 
			@QueryParam("phone")String toUser,
			@QueryParam("content")String content);
}
