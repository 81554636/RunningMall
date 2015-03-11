package ecommerce.rmall.portal.util;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.Shipment;

public class OrderHelper {

	public String format(Shipment ship){
		if( null != ship){
			
			StringBuilder sb = new StringBuilder();
			sb.append("<address><pre>");
				sb.append("小站信息 : <br/>");
				sb.append(String.format("    名称 : %s<br/>", ship.getStation().getName()));
				sb.append(String.format("    城市 : %s<br/><br/>", ship.getStation().getCity()));
			
				sb.append("送货信息 : <br/>");
				sb.append(String.format("    姓名 : %s<br/>", ship.getDelivery().getName()));
				sb.append(String.format("    电话 : %s<br/>", ship.getDelivery().getPhone()));
				sb.append(String.format("    城市 : %s<br/>", ship.getDelivery().getCity()));
				sb.append(String.format("    地址 : %s<br/>", ship.getDelivery().getAddress()));
			sb.append("</pre></address>");
			return sb.toString();
			
		} else
			return "";
	}
	public String format(Order order){
		if( null != order ){
			StringBuilder sb = new StringBuilder();
			sb.append("<address><pre>");
				sb.append(String.format("订单编号 : %d<br/>", order.getId()));
				sb.append("客户信息 : <br/>");
				sb.append(String.format("    姓名 : %s<br/>", order.getCustomer().getName()));
				sb.append(String.format("    电话 : %s<br/>", order.getCustomer().getPhone()));
				
				sb.append("<br/>商品项 : <br/>");
				for(OrderItem item : order.getDetails())
					sb.append(String.format("    %s * %d<br/>", item.getProduct().getDisplayName(), item.getQuantity()));
				
				sb.append("<br/>送货信息 : <br/>");
				sb.append(String.format("    姓名 : %s<br/>", order.getDelivery().getName()));
				sb.append(String.format("    电话 : %s<br/>", order.getDelivery().getPhone()));
				sb.append(String.format("    地址 : %s<br/>", order.getDelivery().getAddress()));
			sb.append("</pre></address>");
			return sb.toString();
		} else
			return "";
	}
}
