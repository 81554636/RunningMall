package ecommerce.rmall.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ecommerce.rmall.domain.Order;

@Path("/Order")
public interface IOrderService {

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Order Place(Order order, @QueryParam("customerID")int customerID);
	
	@GET
	@Path("/{orderID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Order Query(@PathParam("orderID")int orderID);
}
