package ecommerce.rmall.portal.message;

import org.springframework.jms.core.JmsTemplate;

public class MessageSender {
	
	private JmsTemplate jmsTemplate;
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessage(){
		this.jmsTemplate.convertAndSend("hello jms");
	}
}
