package ecommerce.rmall.ws.util;

import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.ws.OrderItemDTO;

public class OrderItemConvert {

	static public OrderItemDTO convert(OrderItem item){
		OrderItemDTO rtn = new OrderItemDTO();
		rtn.setPrice(item.getPrice());
		rtn.setProduct(ProductConvert.convert(item.getProduct()));
		rtn.setQuantity(item.getQuantity());
		return rtn;
	}
}
