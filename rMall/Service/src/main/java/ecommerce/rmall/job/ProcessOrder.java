package ecommerce.rmall.job;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ProcessOrder implements IJob, ApplicationContextAware{

	private ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    	this.ctx = ctx;
    }
    
	@Override
	public void execute(String content) {
		this.ctx.publishEvent(new OrderNotification(content));
	}
}
