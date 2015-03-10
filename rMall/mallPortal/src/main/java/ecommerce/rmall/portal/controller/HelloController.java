package ecommerce.rmall.portal.controller;

import java.util.Collection;
import java.util.List;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.IOrderService;
import ecommerce.rmall.service.IStationService;
import ecommerce.rmall.ws.IShipmentService;

@Controller
@RequestMapping("/")
public class HelloController {
	
	@Autowired()
	@Qualifier("orderService")
	private IOrderService orderService;
	public void setOrderService(IOrderService orderService){
		this.orderService = orderService;
	}
	
	@Autowired()
	@Qualifier("stationService")
	private IStationService stationService;
	public void setStationService(IStationService service){
		this.stationService = service;
	}
	
	@Autowired()
	@Qualifier("shipmentEndpoint")
	private IShipmentService shipmentService;
	public void setStationService(IShipmentService service){
		this.shipmentService = service;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pendingFirst")
	public String printWelcome(Model model){
		return this.printWelcome(model, 1);
		
	}
	@RequestMapping(method=RequestMethod.GET, value="/pendingPages")
	public String printWelcome(Model model, int pageNumber){

		Page<Order> page = this.orderService.queryPendingWithPage(pageNumber);
		List<Station> stations = this.stationService.listAll();
		
		model.addAttribute("CURRENT", "PENDING");
		model.addAttribute("page", page);
		model.addAttribute("stations", stations);
		return "pending";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/processingFirst")
	public String initProcessing(Model model){
		return queryProcessing(model, 1);
	}
	@RequestMapping(method=RequestMethod.GET, value="/processingPages")
	public String queryProcessing(Model model, int pageNumber){
		
		Page<Order> page = this.orderService.queryProcessingWithPage(pageNumber);
		model.addAttribute("CURRENT", "PROCESSING");
		model.addAttribute("page", page);
		return "processing";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/search")
	public String search(Model model){
		
		model.addAttribute("CURRENT", "SEARCH");
		return "search";
	}
	
	@ModelAttribute()
	@RequestMapping(method=RequestMethod.POST, value="/search")
	public String search(Model model, String orderID){
		
		model.addAttribute("CURRENT", "SEARCH");
		Order order = this.orderService.query(Integer.parseInt(orderID));
		List<Station> stations = this.stationService.listAll();
		model.addAttribute("order", order);
		model.addAttribute("stations", stations);
		return "search";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cancel")
	@ResponseBody
    public String cancelOrder(Integer id) {
		this.orderService.cancel(id);
        return "SUCCESS";
    }
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/dispatch")
	@ResponseBody
	public String dispatchOrder(int orderID, int stationID){
		Shipment shipment = this.shipmentService.dispatch(orderID, stationID);
		return "SUCCESS";
	}

	@RequestMapping(method=RequestMethod.POST, value="/send")
	public String sendMessage(String user, String msg){
		
		sendMessageAuto(user, msg);
		return "redirect:../index.html"; 
	}
	
    public static void sendMessageAuto(String userid, String message) {  
        
        final String userId = userid;  
  
        final String autoMessage = "{userId:'ryo', message:'hello'}";  
        final String OP_ID = "userId";
        
        Browser.withAllSessionsFiltered(
        		new ScriptSessionFilter() {
        			public boolean match(ScriptSession session) {
        				if (session.getAttribute(OP_ID) == null)
        					return false;
        				else
        					return session.getAttribute(OP_ID).equals(userId);
        			}
        		},
        		new Runnable() {
        			private ScriptBuffer script = new ScriptBuffer();
        			public void run() {
        				//script.appendCall("showMessage", autoMessage);
        				script.appendScript("showMessage("+autoMessage+")");
        				Collection<ScriptSession> sessions = Browser.getTargetSessions();
        				for (ScriptSession scriptSession : sessions)
        					scriptSession.addScript(script);
  
        			}
        		});  
  
    }  
}