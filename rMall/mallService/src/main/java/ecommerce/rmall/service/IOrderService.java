package ecommerce.rmall.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ecommerce.rmall.domain.Order;

@Path("/Order")
public interface IOrderService {

	@POST
	@Path("/Place/{customer}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Order Place(Order order, @PathParam("customer")long customerID);
	
	@GET
	@Path("/Query/{order}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Order Query(@PathParam("order")long orderID);
}
