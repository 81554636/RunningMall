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

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;

@WebService(endpointInterface="ecommerce.rmall.webservice", serviceName="Order")
@Path("/Order")
public interface IOrderService {
	
	@POST
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	Order Place(Order order);

	@POST
	@Path("/Customer/{customerID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Order Place(Order order, @PathParam("customerID")int customerID);
	
	@PUT
	@Path("/{orderID}/cancellation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	void Cancel(@PathParam("orderID")int orderID);
	
	@PUT
	@Path("/{orderID}/finish")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	void Finish(@PathParam("orderID")int orderID);
	
	@GET
	@Path("/{orderID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Order Query(@PathParam("orderID")int orderID);
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Page<Order> Query(@QueryParam("phone") String phone, @QueryParam("pageNumber")int pageNumber);
}
