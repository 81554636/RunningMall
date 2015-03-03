package ecommerce.rmall.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
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
	
	@RequestMapping(method=RequestMethod.GET, value="/search")
	public String search(){
		return "search";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/search")
	public String search(Model model, String orderID){
		Order order = this.orderService.query(Integer.parseInt(orderID));
		model.addAttribute("order", order);
		return "search";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/send")
	public String sendMessage(String user, String msg){
		
		//try {
		//	String str = new String(msg.getBytes("iso-8859-1"),"UTF-8");
			sendMessageAuto(user, msg);
		///} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}		
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