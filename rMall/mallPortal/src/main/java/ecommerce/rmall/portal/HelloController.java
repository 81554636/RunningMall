package ecommerce.rmall.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class HelloController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String printWelcome(){
		return "hello";
	}

}
