package ecommerce.rmall.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ecommerce.rmall.domain.Shipment;

@Path("/Shipment")
public interface IShipmentService {
	
	@POST
	@Path("/Distribution")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Shipment dispatch(@QueryParam("order")int orderID, @QueryParam("station")int stationid);
}
