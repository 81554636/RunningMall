package ecommerce.rmall.ws.util;

import java.util.HashSet;
import java.util.Set;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.ws.OrderDTO;
import ecommerce.rmall.ws.OrderItemDTO;

public class OrderConvert {
	static public OrderDTO convert(Order order){
		
		OrderDTO rtn = new OrderDTO();
		rtn.setDelivery(DeliveryConvert.convert(order.getDelivery()));
		rtn.setOwner(CustomerConvert.convert(order.getCustomer()));
		Set<OrderItemDTO> setOfItems = new HashSet<OrderItemDTO>();
		for(OrderItem item : order.getDetails())
			setOfItems.add(OrderItemConvert.convert(item));
		rtn.setDetails(setOfItems);
		return rtn;
	}
}
