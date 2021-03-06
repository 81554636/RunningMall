package ecommerce.rmall.ws;

import javax.jws.WebService;
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

import ecommerce.rmall.domain.Coupon;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;

@WebService(endpointInterface="ecommerce.rmall.webservice", serviceName="CustomerService")
@Path("/Customer")
public interface ICustomerService {
	
	// ++++++++++ Customer ++++++++++
	@POST
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	Customer register(Customer customer);
	
	@PUT
	@Path("/{sessionKey}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	Customer update(@PathParam("sessionKey")String sessionKey, Customer customer);
	
	@PUT
	@Path("/{sessionKey}/activation")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	String activate(@PathParam("sessionKey")String sessionKey);
	
	@PUT
	@Path("/{userName}/password/init")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	String requestPassword(@PathParam("userName")String userName);
	
	@PUT
	@Path("/{sessionKey}/password/reset")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	Customer resetPassword(@PathParam("sessionKey")String userName, @QueryParam("passDigest")String passDigest);
	
	@GET
	@Path("/{userName}/signIn")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	Customer signIn(@PathParam("userName")String userName, @QueryParam("passDigest")String passDigest);
	
	@GET
	@Path("/{userName}/validation")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	Customer validate(@PathParam("userName")String userName, @QueryParam("captcha")String captcha);

	@PUT
	@Path("/{sessionKey}/signOut")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	String signOut(@PathParam("sessionKey")String sessionKey);
	
	// ++++++++++ Customer's COUPONs ++++++++++ 
	@GET
	@Path("/{sessionKey}/coupons/pagination/{pageNumber}")
	@Produces({MediaType.APPLICATION_JSON})
	Page<Coupon> fetchCoupons(@PathParam("sessionKey")String sessionKey, 
			@DefaultValue("1")@PathParam("pageNumber")int pageNumber);
	
	// ++++++++++ Customer's ORDERs ++++++++++ 
	@POST
	@Path("/{sessionKey}/order")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	Order Place(@PathParam("sessionKey")String sessionKey, 
			Order order);
	
	@POST
	@Path("/{sessionKey}/activity/{activityID}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	Order Place(@PathParam("sessionKey")String sessionKey, 
			@PathParam("activityID")int activityID, 
			Order order);
	
	@GET
	@Path("/{sessionKey}/order/{orderID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Order Query(@PathParam("sessionKey")String sessionKey, 
			@PathParam("orderID")int orderID);
	
	@GET
	@Path("/{sessionKey}/orders/pagination/{pageNumber}")
	@Produces({MediaType.APPLICATION_JSON})
	Page<Order> orderPagination(@PathParam("sessionKey")String sessionKey, 
			@DefaultValue("1")@PathParam("pageNumber")int pageNumber);
	
	@GET
	@Path("/{sessionKey}/orders/pagination/{pageNumber}/status/{status}")
	@Produces({MediaType.APPLICATION_JSON})
	Page<Order> orderPaginationByStatus(@PathParam("sessionKey")String sessionKey, 
			@PathParam("status")Status status,
			@DefaultValue("1")@PathParam("pageNumber")int pageNumber);
	
	@PUT
	@Path("/{sessionKey}/order/{orderID}/cancellation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String Cancel(@PathParam("sessionKey")String sessionKey, 
			@PathParam("orderID")int orderID);
	
	@PUT
	@Path("/{sessionKey}/order/{orderID}/finish")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String Finish(@PathParam("sessionKey")String sessionKey, 
			@PathParam("orderID")int orderID);
}
