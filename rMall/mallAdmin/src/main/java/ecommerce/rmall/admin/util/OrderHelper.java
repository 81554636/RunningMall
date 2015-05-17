package ecommerce.rmall.admin.util;

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
			
				sb.append("收件人信息 : <br/>");
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
					sb.append(String.format("    %s(%s) * <font color='RED'>%d</font><br/>", item.getProduct().getDisplayName(), item.getSpec().getName(), item.getQuantity()));					
				
				sb.append("<br/>收件人信息 : <br/>");
				sb.append(String.format("    姓名 : %s<br/>", order.getDelivery().getName()));
				sb.append(String.format("    电话 : %s<br/>", order.getDelivery().getPhone()));
				sb.append(String.format("    地址 : %s<br/>", order.getDelivery().getAddress()));
				if(order.getAccessCode() != null)
					sb.append(String.format("  验证码 : <B>%s</B><br/>", order.getAccessCode()));
			sb.append("</pre></address>");
			return sb.toString();
			
		} else
			return "";
	}
}
