package ecommerce.rmall.ws;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ecommerce.rmall.domain.Channel;
import ecommerce.rmall.domain.Station;

@WebService
@Path("/Station")
public interface IStationService {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Station signIn(@QueryParam("userName")String username, @QueryParam("password")String password);

	@PUT
	@Path("/filter/sessionKey/{sessionKey}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateChannel(@PathParam("sessionKey")String sessionKey, 
			@QueryParam("channel")long channelID, 
			@QueryParam("userID")String userID, 
			@QueryParam("OS")String osType);
	
	@POST
	@Path("/filter/sessionKey/{sessionKey}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateChannel(@PathParam("sessionKey")String sessionKey, Channel channel);
}
