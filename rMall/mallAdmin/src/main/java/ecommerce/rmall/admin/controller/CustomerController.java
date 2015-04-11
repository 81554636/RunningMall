package ecommerce.rmall.admin.controller;

import java.security.Principal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.ICustomerOrderService;
import ecommerce.rmall.service.ICustomerService;
import ecommerce.rmall.service.IProductService;
import ecommerce.rmall.service.IStationService;

@Controller
@RequestMapping("/Customer")
public class CustomerController {
	
	@Autowired()
	@Qualifier("customerService")
	private ICustomerService service;
	
	@Autowired()
	@Qualifier("customerOrderService")
	private ICustomerOrderService orderSerivce;
	
	@Autowired()
	@Qualifier("stationService")
	private IStationService stationService;
	
	@Autowired()
	@Qualifier("productService")
	private IProductService productService;
	
	@RequestMapping(method=RequestMethod.GET, value="/initRegister")
	public String preRegister(Model model, Principal principal){

		model.addAttribute("CURRENT", "REGISTER");
		model.addAttribute("DISPLAYNAME", principal == null ? "UNKNOWN" : principal.getName());
		return "Customer/viewRegister";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/register")
	@ResponseBody
	public Customer register(@RequestBody Customer customer){
		return this.service.create(customer);
	}
	
	@RequestMapping(method={RequestMethod.POST, RequestMethod.GET}, value="/search")
	public String search(Model model, String customerID, Principal principal){
		
		model.addAttribute("CURRENT", "CUSTOMER");
		model.addAttribute("DISPLAYNAME", principal == null ? "UNKNOWN" : principal.getName());
		if(customerID != null){
			Page<Customer> page = this.service.findCustomer(customerID);
			List<Product> products = this.productService.listAll();
			List<Station> stations = this.stationService.listAll();
			
			model.addAttribute("stations", stations);
			model.addAttribute("products", products);
			model.addAttribute("page", page);
		}
		return "Customer/search";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/credential/username/{username}")
	@ResponseBody
	public Customer findByUsername(@PathVariable("username")String username){
		
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(username);
		System.out.println(m.matches()+"---");
		return this.service.findByUsername(username);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{customerID}/order")
	@ResponseBody
	public Order createOrder(@RequestBody Order order, @PathVariable("customerID")int customerID){
		
		return this.orderSerivce.Place(customerID, order.getDelivery(), order.getDetails());
	}
}