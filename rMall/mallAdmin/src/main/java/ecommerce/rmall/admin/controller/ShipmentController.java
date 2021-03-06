package ecommerce.rmall.admin.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ecommerce.rmall.ws.IShipmentService;

@Controller
@RequestMapping("/Shipment")
public class ShipmentController {
	
	@Autowired()
	@Qualifier("shipmentService")
	private ecommerce.rmall.service.IShipmentService service;
	
	@Autowired
	@Qualifier("shipmentEndpoint")
	private IShipmentService shipmentEndpoint;
	
	@RequestMapping(method=RequestMethod.GET, value="/first")
	public String first(Model model, Principal principal){

		return this.page(model, 1, null, principal);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/page")
	public String page(Model model, int pageNumber,
			@RequestParam(value="station", required=false)String station, Principal principal){
		
		model.addAttribute("DISPLAYNAME", principal == null ? "UNKNOWN" : principal.getName());
		model.addAttribute("CURRENT", "SHIPMENT");
		if(null != station)
			model.addAttribute("page", this.service.queryByNameWithPage(station, pageNumber));
		else
			model.addAttribute("page", this.service.queryWithPage(pageNumber));
		return "Shipment/pagination";
	}
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/{shipmentID}/notify")
	@ResponseBody
	public String notify(@PathVariable("shipmentID")int shipmentID){
		
		return this.shipmentEndpoint.notify(shipmentID);
	}
}