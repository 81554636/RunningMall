package ecommerce.rmall.portal.dwr;

import java.net.URLEncoder;
import java.util.Collection;

import javax.servlet.ServletContext;

import org.directwebremoting.ScriptBuffer;  
import org.directwebremoting.ScriptSession;  
import org.directwebremoting.ServerContext;  
import org.directwebremoting.ServerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;  
import org.springframework.web.context.ServletContextAware;

import ecommerce.rmall.domain.Customer;

/** 
 * MessageEvent事件的监听类 
 * 需要spring的支持 利用spring的消息驱动机制 
 * @author frank59 
 * 
 */  
@SuppressWarnings("unchecked")  
public class ChatMessageClient implements ApplicationListener<ChatMessageEvent>, ServletContextAware {  
    
	private static Logger logger = LoggerFactory.getLogger(ChatMessageClient.class);
	private ServletContext ctx;
    @Override public void setServletContext(ServletContext arg0) { this.ctx = arg0; }

	@Override
	public void onApplicationEvent(ChatMessageEvent event) {
		
        ServerContext context = ServerContextFactory.get(ctx);
        // 获得客户端所有chat页面script session连接数  
        Collection<ScriptSession> sessions = context.getScriptSessionsByPage(ctx.getContextPath() + "/chat.jsp");          
        for (ScriptSession session : sessions) {
        	
            ScriptBuffer sb = new ScriptBuffer();
            String order = (String)event.getSource();
            Customer customer = new Customer();
            customer.setAddress("虹梅路1801号");
            customer.setName("momo");
            customer.setPhone("10010");
            
            //sb.appendScript("showCustomer(").appendScript(new com.google.gson.Gson().toJson(customer)).appendScript(")");
            sb.appendScript("showCustomer(").appendScript(order).appendScript(")");
            //String data = String.format("{msg:'%s', time:'%s'}", msg.getMsg(), s);
            //sb.appendScript("showMessage(").appendScript(data).appendScript(")");
            logger.debug(sb.toString());
            //执行客户端script session方法，相当于浏览器执行JavaScript代码                     
            //上面就会执行客户端浏览器中的showMessage方法，并且传递一个对象过去
            session.addScript(sb);
        }
	}  
}  