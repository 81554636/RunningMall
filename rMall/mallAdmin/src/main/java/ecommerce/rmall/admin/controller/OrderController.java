package ecommerce.rmall.admin.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ecommerce.rmall.domain.CountByDate;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Specification;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.IProductService;
import ecommerce.rmall.service.ISpecificationService;
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
	
	@Autowired()
	@Qualifier("productService")
	private IProductService productService;
	
	@Autowired()
	@Qualifier("specificationService")
	private ISpecificationService specificationService;
	
	@Autowired
	@Qualifier("shipmentEndpoint")
	private IShipmentService shipmentEndpoint;
	
	@RequestMapping(method=RequestMethod.GET, value="/pending/First")
	public String pendingFirst(Model model, Principal principal){
		return this.pendingPage(model, 1, principal);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/pending/Pages")
	public String pendingPage(Model model, int pageNumber, Principal principal){
		
		Page<Order> page = this.service.queryPendingWithPage(pageNumber);
		List<Station> stations = this.stationService.listAll();
		List<Specification> specs = this.specificationService.listAll();
		
		model.addAttribute("DISPLAYNAME", principal!=null?principal.getName() : "UNKNOWN");
		model.addAttribute("CURRENT", "PENDING");
		model.addAttribute("page", page);
		model.addAttribute("stations", stations);
		model.addAttribute("specs", specs);
		return "Order/pending";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/processing/First")
	public String processingFirst(Model model, Principal principal){
		return this.processingPage(model, 1, principal);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/processing/Pages")
	public String processingPage(Model model, int pageNumber, Principal principal){

		Page<Order> page = this.service.queryProcessingWithPage(pageNumber);
		List<Station> stations = this.stationService.listAll();
		
		model.addAttribute("DISPLAYNAME", principal!=null?principal.getName() : "UNKNOWN");
		model.addAttribute("CURRENT", "PROCESSING");
		model.addAttribute("page", page);
		model.addAttribute("stations", stations);
		return "Order/processing";
	}

	@RequestMapping(method={RequestMethod.POST, RequestMethod.GET}, value="/search")
	public String search(Model model, @RequestParam(value="orderID", required=false)String orderID, Principal principal){
		
		model.addAttribute("DISPLAYNAME", principal!=null?principal.getName() : "UNKNOWN");
		model.addAttribute("CURRENT", "SEARCH");
		if(null != orderID){
			Order order = this.service.query(Integer.parseInt(orderID));
			List<Station> stations = this.stationService.listAll();
			List<Specification> specs = this.specificationService.listAll();
			
			model.addAttribute("order", order);
			model.addAttribute("stations", stations);
			model.addAttribute("specs", specs);
		}
		return "Order/search";
	}
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/stastic")
	public String stastic(Model model, Principal principal){
		
		model.addAttribute("DISPLAYNAME", principal != null ? principal.getName() : "UNKNOWN");
		return "Order/stastic";
	}
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/{orderID}/dispatch")
	@ResponseBody
	public String dispatchOrder(@PathVariable("orderID")int orderID, @RequestParam(value="stationID", required=true)int stationID){

		Shipment shipment = this.shipmentEndpoint.dispatch(orderID, stationID);
		logger.debug("generate new shipment {id:{}, station:{}, delivery:{}}", 
				shipment.getId(),
				shipment.getStation().getName(),
				shipment.getDelivery().getName());
		return "SUCCESS";
	}
	
	//@RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/notify")
	//@ResponseBody
	//public String notify(@RequestParam(value="shipmentID", required=true)int shipmentID){
	//	return this.shipmentEndpoint.notify(shipmentID);
	//}
	
	@RequestMapping(method={RequestMethod.POST}, value="/create")
	@ResponseBody
	public String createOrder(@RequestBody Order order){
		
		this.service.placeAndDistribute(order.getDelivery(), order.getDetails());
   		return "SUCCESS";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{orderID}/cancel")
	@ResponseBody
    public String cancelOrder(@PathVariable("orderID")Integer id) {
		
		this.service.cancel(id);
        return "SUCCESS";
    }

	@RequestMapping(method={RequestMethod.POST, RequestMethod.GET}, value="/{orderID}/modify")
	@ResponseBody
	public String modifyOrder(@PathVariable("orderID")int orderID, @RequestBody Order order){

		this.service.update(order);
   		return "SUCCESS";
	}
	
	@RequestMapping(method={RequestMethod.POST, RequestMethod.GET}, value="/{orderID}/query")
	@ResponseBody
	public Order queryOrder(@PathVariable("orderID")int orderID){
		
		Order order = this.service.query(orderID);
		return order;
	}
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET}, value="/countByDate")
	@ResponseBody
	public List<Object> countByDate(){
		List<CountByDate> rtn = this.service.countByDate();
		List<Integer> data = new ArrayList<Integer>();
		List<String> categories = new ArrayList<String>();
		Serie serie = new Serie("Order");
		for(CountByDate item : rtn){
			data.add(item.getCount());
			categories.add(item.getDate());
		}
		serie.setData(data);
		
		List<Object> series = new ArrayList<Object>();
		series.add(serie);
		
		List<Object> result = new ArrayList<Object>();
		result.add(categories);
		result.add(series);
		
		return result;
	}
}
