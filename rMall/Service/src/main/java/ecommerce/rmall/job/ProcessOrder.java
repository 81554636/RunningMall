package ecommerce.rmall.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ProcessOrder implements IJob, ApplicationContextAware{

	private static Logger logger = LoggerFactory.getLogger(ProcessOrder.class);
	
	private ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    	this.ctx = ctx;
    }
    
	@Override
	public void execute(String content) {
		
		logger.info("publish ORDER ( {} );", content);
		this.ctx.publishEvent(new OrderNotification(content));
	}
}
