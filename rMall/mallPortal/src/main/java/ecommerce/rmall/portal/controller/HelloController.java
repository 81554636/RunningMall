package ecommerce.rmall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.service.IOrderService;

@Controller
@RequestMapping("/welcome")
public class HelloController {
	
	@Autowired()
	@Qualifier("orderService")
	private IOrderService orderService;
	public void setOrderService(IOrderService orderService){
		this.orderService = orderService;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/first")
	public String printWelcome(Model model){
		return this.printWelcome(model, 1);
		
	}
	@RequestMapping(method=RequestMethod.GET, value="/pages")
	public String printWelcome(Model model, int pageNumber){
		
		Page<Order> page = this.orderService.queryWithPage(pageNumber);
		model.addAttribute("page", page);
		return "hello";
	}
}