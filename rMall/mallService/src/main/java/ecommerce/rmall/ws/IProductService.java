package ecommerce.rmall.ws;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;

@WebService
@Path("/Product")
public interface IProductService {
	
	@GET
	@Path("/pagination")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Page<Product> queryWithPage(@DefaultValue("1")@QueryParam("pageNumber")int pageNumber);
	
	@GET
	@Path("/pagination/{pageNumber}/byCustomer/{sessionKey}")
	@Produces(MediaType.APPLICATION_JSON)
	Page<Product> queryPagination(@PathParam("sessionKey")String sessionKey,
			@DefaultValue("1")@PathParam("pageNumber")int pageNumber);
}
