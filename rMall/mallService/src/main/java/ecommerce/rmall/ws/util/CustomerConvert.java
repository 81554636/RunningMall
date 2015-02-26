package ecommerce.rmall.ws.util;

import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.ws.CustomerDTO;

public class CustomerConvert {

	static public CustomerDTO convert(Customer customer){
		CustomerDTO rtn = new CustomerDTO();
		rtn.setAddress(customer.getAddress());
		rtn.setName(customer.getName());
		rtn.setPhone(customer.getPhone());
		return rtn;
	}
}
