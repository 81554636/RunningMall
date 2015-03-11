package ecommerce.rmall.job;

import org.springframework.context.ApplicationEvent;

public class OrderNotification extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;

	public OrderNotification(Object source) {
		super(source);
	}
}
