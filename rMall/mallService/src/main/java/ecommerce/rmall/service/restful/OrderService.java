package ecommerce.rmall.service.restful;

import java.util.Date;

import javax.annotation.Resource;

import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.service.ICustomerService;
import ecommerce.rmall.service.IOrderService;

public class OrderService implements IOrderService {

	@Resource(name="customerService")
	private ICustomerService service;
	public void setService(ICustomerService service) {
		this.service = service;
	}

	@Override
	public void Place(String content) {
		Customer customer = new Customer();
		customer.setAddress("莲溪路780弄21号301室");
		customer.setName("凌小舟");
		customer.setCreateDate(new Date());
		customer.setLastUpdate(new Date());

		this.service.saveCustomer(customer);
		System.out.println(content);
	}
}
