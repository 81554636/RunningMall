package ecommerce.rmall.message;

import org.springframework.jms.core.JmsTemplate;

public class MessageSender {
	
	private JmsTemplate jmsTemplate;
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessage(String message){
		this.jmsTemplate.convertAndSend(message);
	}
	
	public void sendObject(Object object){
		this.jmsTemplate.convertAndSend(object);
	}
}
