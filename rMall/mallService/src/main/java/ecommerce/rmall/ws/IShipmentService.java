package ecommerce.rmall.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;

@Path("/Shipment")
public interface IShipmentService {
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON})
	Shipment dispatch(@QueryParam("order")int orderID, 
			@QueryParam("station")int stationid);
	
	@GET
	@Path("/{shipmentID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Shipment queryByID(@PathParam("shipmentID")int shipmentID);
	
	@PUT
	@Path("/{shipmentID}/notify")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String notify(@PathParam("shipmentID")int shipmentID);
	
	@PUT
	@Path("/{shipmentID}/finish")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String finish(@PathParam("shipmentID")int shipmentID, 
			@QueryParam("accessCode")String accessCode);
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Page<Shipment> queryByStation(@QueryParam("sessionKey")String sessionKey, 
			@DefaultValue("1")@QueryParam("pageNumber")int pageNumber);
}
