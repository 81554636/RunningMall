package ecommerce.rmall.portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Shipment")
public class ShipmentController {
	
	@Autowired()
	@Qualifier("shipmentService")
	private ecommerce.rmall.service.IShipmentService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/first")
	public String first(Model model){

		return this.page(model, 1, null);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/page")
	public String page(Model model, 
			int pageNumber, 
			@RequestParam(value="station", required=false)String station){
		
		model.addAttribute("CURRENT", "SHIPMENT");
		if(null != station)
			model.addAttribute("page", this.service.queryByNameWithPage(station, pageNumber));
		else
			model.addAttribute("page", this.service.queryWithPage(pageNumber));
		return "Shipment/pagination";
	}
}