package ecommerce.rmall.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecommerce.rmall.job.IJob;

public class MessageReceiver implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	private IJob job;
	public void setJob(IJob job){
		this.job = job;
	}
	
	@Override
	public void onMessage(Message message) {
		
		logger.info("new message receive");
		if(message instanceof TextMessage){
			TextMessage om = (TextMessage) message;
			try {
				logger.debug("{ messageId:{}, destination:{} }", om.getJMSMessageID(), om.getJMSDestination());
	            String content = om.getText();
	            this.job.execute(content);

	        } catch (JMSException e) {  
	            e.printStackTrace();  
	        }
		}
	}
}
