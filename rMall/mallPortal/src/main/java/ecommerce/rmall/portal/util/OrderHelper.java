package ecommerce.rmall.portal.util;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;

public class OrderHelper {
	
	public String format(Order order){
		StringBuilder sb = new StringBuilder();
		sb.append("<address><pre>");
			sb.append(String.format("订单编号 : %d<br/>", order.getId()));
			sb.append("客户信息 : <br/>");
			sb.append(String.format("    姓名 : %s<br/>", order.getCustomer().getName()));
			sb.append(String.format("    电话 : %s<br/>", order.getCustomer().getPhone()));
			sb.append("商品项 : <br/>");
			for(OrderItem item : order.getDetails())
				sb.append(String.format("    %s * %d<br/>", item.getProduct().getDisplayName(), item.getQuantity()));
			sb.append("送货信息 : <br/>");
			sb.append(String.format("    姓名 : %s<br/>", order.getDelivery().getName()));
			sb.append(String.format("    电话 : %s<br/>", order.getDelivery().getPhone()));
			sb.append(String.format("    地址 : %s<br/>", order.getDelivery().getAddress()));
		sb.append("</pre></address>");
		return sb.toString();
	}
}
