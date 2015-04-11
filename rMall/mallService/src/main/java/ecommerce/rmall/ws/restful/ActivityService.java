package ecommerce.rmall.ws.restful;

import java.util.List;

import ecommerce.rmall.domain.Activity;
import ecommerce.rmall.ws.IActivityService;

public class ActivityService implements IActivityService {

	private ecommerce.rmall.service.IActivityService service = null;
	public void setActivityService(ecommerce.rmall.service.IActivityService service){
		this.service = service;
	}
	
	@Override
	public List<Activity> listAllValid() {
		return this.service.listValid();
	}
}
