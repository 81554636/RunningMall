package ecommerce.rmall.service;

import java.util.List;

import ecommerce.rmall.domain.Delivery;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.Page;

public interface ICustomerOrderService {
	
	/***
	 * 客户下订单
	 * @param sessionKey	客户会话
	 * @param delivery	送货信息
	 * @param items	订单内容
	 * @return
	 */
	Order Place(String sessionKey, Delivery delivery, List<OrderItem> items);
	
	/***
	 * 客户下订单
	 * @param customerID	客户ID
	 * @param delivery	送货信息
	 * @param items	订单内容
	 * @return
	 */
	Order Place(int customerID, Delivery delivery, List<OrderItem> items);
	
	/***
	 * 客户下单(活动入口)
	 * @param sessionKey	客户会话
	 * @param delivery	送货信息
	 * @param activity	活动内容
	 * @return
	 */
	Order Join(String sessionKey, Delivery delivery, List<OrderItem> items, int activityID);
	
	/***
	 * 订单查询
	 * @param sessionKey
	 * @param orderID	订单编号
	 * @return
	 */
	Order QueryByID(String sessionKey, int orderID);
	
	/***
	 * 订单查询(分页)
	 * @param sessionKey
	 * @param pageNumber
	 * @return
	 */
	Page<Order> ordersPagination(String sessionKey, int pageNumber);
	
	/***
	 * 取消订单
	 * @param sessionKey
	 * @param orderID
	 */
	void Cancel(String sessionKey, int orderID);
	
	/***
	 * 确认订单完成
	 * @param sessionKey
	 * @param orderID
	 */
	void Finish(String sessionKey, int orderID);
}
