package ecommerce.rmall.admin.dwr;


import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.springframework.context.ApplicationListener;

import ecommerce.rmall.job.OrderNotification;

public class MessagePushClient implements ApplicationListener<OrderNotification>{

	@Override
	public void onApplicationEvent(OrderNotification event) {
		sendMessageAuto(null, (String)event.getSource());
	}
	
	public static void sendMessageAuto(String userid, String message) {  
        
        final String userId = userid;
        final String autoMessage = message;
        final String OP_ID = "userId";
        ScriptSessionFilter filter = new ScriptSessionFilter() {
			public boolean match(ScriptSession session) {
				if (session.getAttribute(OP_ID) == null)
					return false;
				else
					return session.getAttribute(OP_ID).equals(userId);
			}
		};
		
        Runnable job = new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();
			public void run() {

				script.appendScript("showMessage(");
				script.appendScript(JavascriptUtil.escapeJavaScript(autoMessage, false));
				script.appendScript(");");
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				for (ScriptSession scriptSession : sessions)
					scriptSession.addScript(script);
			}
		};
		
		if(null == userid)
			Browser.withAllSessions(job);
		else
			Browser.withAllSessionsFiltered(filter,job);
    }
}
