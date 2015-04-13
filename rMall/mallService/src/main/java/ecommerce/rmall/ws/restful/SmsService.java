package ecommerce.rmall.ws.restful;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ecommerce.rmall.notify.moming.ISmsEndpoint;

@WebService
@Path("/Sms")
public class SmsService {
	
	private ISmsEndpoint sms;
	public void setSms(ISmsEndpoint sms){this.sms = sms;}
	
	@GET
	@Path("/Balance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getBalance(){
		
		return this.sms.getBalance("getBalance", "70204547", "10576cc8eaef6e3f9748ea3a3793afaf");
	}
	
	@POST
	@Path("/{phone}/{content}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sendMessage(@PathParam("phone")String phone, @PathParam("content")String content){
		
		return this.sms.sendMessage("send", "70204547", "10576cc8eaef6e3f9748ea3a3793afaf", "utf8", 
				phone, content);
	}
}
