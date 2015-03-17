package ecommerce.rmall.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.IStationService;
import ecommerce.rmall.ws.IShipmentService;

@Controller
@RequestMapping("/Order")
public class OrderController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired()
	@Qualifier("orderService")
	private ecommerce.rmall.service.IOrderService service;
	
	@Autowired()
	@Qualifier("stationService")
	private IStationService stationService;
	
	@Autowired
	@Qualifier("shipmentEndpoint")
	private IShipmentService shipmentEndpoint;
	
	@RequestMapping(method=RequestMethod.GET, value="/pendingFirst")
	public String pendingFirst(Model model){
		return this.pendingPage(model, 1);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pendingPages")
	public String pendingPage(Model model, int pageNumber){
		
		Page<Order> page = this.service.queryPendingWithPage(pageNumber);
		List<Station> stations = this.stationService.listAll();
		
		model.addAttribute("CURRENT", "PENDING");
		model.addAttribute("page", page);
		model.addAttribute("stations", stations);
		return "Order/pendingPagination";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/processingFirst")
	public String processingFirst(Model model){
		return this.processingPage(model, 1);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/processingPages")
	public String processingPage(Model model, int pageNumber){
		
		Page<Order> page = this.service.queryProcessingWithPage(pageNumber);
		List<Station> stations = this.stationService.listAll();
		
		model.addAttribute("CURRENT", "PROCESSING");
		model.addAttribute("page", page);
		model.addAttribute("stations", stations);
		return "Order/processingPagination";
	}

	@RequestMapping(method={RequestMethod.POST, RequestMethod.GET}, value="/search")
	public String search(Model model, @RequestParam(value="orderID", required=false)String orderID){
		
		model.addAttribute("CURRENT", "SEARCH");
		if(null != orderID){
			Order order = this.service.query(Integer.parseInt(orderID));
			List<Station> stations = this.stationService.listAll();
			model.addAttribute("order", order);
			model.addAttribute("stations", stations);
		}
		return "Order/search";
	}
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/dispatch")
	@ResponseBody
	public String dispatchOrder(int orderID, int stationID){

		Shipment shipment = this.shipmentEndpoint.dispatch(orderID, stationID);
		logger.debug("generate new shipment {id:{}, station:{}, delivery:{}}", 
				shipment.getId(), 
				shipment.getStation().getName(),
				shipment.getDelivery().getName());
		return "SUCCESS";
	}
}
