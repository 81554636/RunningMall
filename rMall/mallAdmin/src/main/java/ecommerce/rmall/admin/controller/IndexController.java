package ecommerce.rmall.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@Autowired()
	@Qualifier("orderService")
	private ecommerce.rmall.service.IOrderService service;
	
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST}, value="dashboard")
	public String welcome(Model model){
		
		Map<String,Integer> orders = this.service.countByStatus();
		model.addAttribute("Orders", orders);
		return "welcome";
	}
}
