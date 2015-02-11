package ecommerce.rmall.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;

@Path("/Order")
public interface IOrderService {

	@POST
	@Path("/Place")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	void Place(String content);
}
