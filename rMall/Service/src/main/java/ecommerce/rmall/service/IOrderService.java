package ecommerce.rmall.service;

import java.util.Set;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Delivery;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;

public interface IOrderService {

	/***
	 * 下订单(已知客户信息)
	 * @param delivery 送货信息
	 * @param items 订购商品货物信息
	 * @param customerID 客户标识
	 * @return
	 */
	Order place(Delivery delivery, Set<OrderItem> items, int customerID);
	/***
	 * 订单查询(根据订单号)
	 * @param orderID
	 * @return
	 */
	Order query(int orderID);
	
	/***
	 * 查询订单(分页)
	 * @param pageNumber 1,2,3.....
	 * @return
	 */
	Page<Order> queryWithPage(int pageNumber);
	
	/***
	 * 查询订单(分页),根据客户手机号查询
	 * @param phone 客户注册时的手机号码
	 * @param pageNumber 1,2,3......
	 * @return
	 */
	Page<Order> queryByPhoneWithPage(String phone, int pageNumber);
}
