package ecommerce.rmall.ws.util;

import ecommerce.rmall.domain.Delivery;
import ecommerce.rmall.ws.DeliveryDTO;

public class DeliveryConvert {

	static public DeliveryDTO convert(Delivery delivery){
		DeliveryDTO rtn = new DeliveryDTO();
		rtn.setAddress(delivery.getAddress());
		rtn.setContact(delivery.getPhone());
		rtn.setDeliveryDate(delivery.getDeliverDate());
		rtn.setReceiver(delivery.getName());
		return rtn;
	}
}
