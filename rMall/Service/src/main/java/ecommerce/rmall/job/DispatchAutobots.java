package ecommerce.rmall.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.service.IShipmentService;

public class DispatchAutobots implements IJob {

	private static Logger logger = LoggerFactory.getLogger(DispatchAutobots.class);

	private Gson jsonConvert = new Gson();
	private IShipmentService shipmentService;
	public void setShipmentService(IShipmentService service){
		this.shipmentService = service;
	}
	
	@Override
	public void execute(String content) {
		
		logger.info("dispatch Order ( {} );", content);
		Order order = this.jsonConvert.fromJson(content, Order.class);
		this.shipmentService.dispatch(order);
	}
}
