package ecommerce.rmall.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ecommerce.rmall.domain.Shipment;

@Path("/Shipment")
public interface IShipmentService {
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Shipment dispatch(@QueryParam("order")int orderID, @QueryParam("station")int stationid);
	
	@GET
	@Path("/{shipmentID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Shipment queryByID(@PathParam("shipmentID")int shipmentID);
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	List<Shipment> queryByStation(@QueryParam("stationID")int stationID);
}
