package ecommerce.rmall.portal.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import ecommerce.rmall.portal.dwr.ChatMessageEvent;

public class MessageReceiver implements MessageListener, ApplicationContextAware {

	private static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
    private ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

	@Override
	public void onMessage(Message message) {

		logger.info("new message receive");
		if(message instanceof TextMessage){
			TextMessage om = (TextMessage) message;
			try {
				logger.debug("{ messageId:{}, destination:{} }", om.getJMSMessageID(), om.getJMSDestination());
	            String content = om.getText();
	            this.ctx.publishEvent(new ChatMessageEvent(content));

	        } catch (JMSException e) {  
	            e.printStackTrace();  
	        }
			
		} else if(message instanceof ObjectMessage){
			
			ObjectMessage om = (ObjectMessage) message;
	        try {  
	            String key1 = om.getStringProperty("key1");  
	              
	            System.out.println(key1);  
	              
	            System.out.println("model:"+om.getJMSDeliveryMode());  
	            System.out.println("destination:"+om.getJMSDestination());  
	            System.out.println("type:"+om.getJMSType());  
	            System.out.println("messageId:"+om.getJMSMessageID());  
	            System.out.println("time:"+om.getJMSTimestamp());  
	            System.out.println("expiredTime:"+om.getJMSExpiration());  
	            System.out.println("priority:"+om.getJMSPriority());  
	        } catch (JMSException e) {  
	            e.printStackTrace();  
	        }
		}
	}
}