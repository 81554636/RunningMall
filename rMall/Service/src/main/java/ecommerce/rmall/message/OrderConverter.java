package ecommerce.rmall.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import ecommerce.rmall.domain.Order;

public class OrderConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {

		return session.createObjectMessage(new com.google.gson.Gson().toJson(object));
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		
		ObjectMessage objMessage = (ObjectMessage) message;
		return new com.google.gson.Gson().fromJson((String)objMessage.getObject(), Order.class);
	}

}
