package ecommerce.rmall.service;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Order;

public interface IShipmentService {
	/***
	 * 订单派送
	 * @param order 订单
	 * @param stationID 小站ID
	 * @return
	 */
	Shipment dispatch(Order order, int stationID);
	/***
	 * 订单派送（根据order.delivery.city来选择相应的小站）
	 * @param order 订单
	 * @return
	 */
	Shipment dispatch(Order order);
	
	Shipment queryByID(int shipmentID);
	Page<Shipment> queryWithPage(int pageNumber);
	Page<Shipment> queryBySessionWithPage(String sessionKey, int pageNumber);
	Page<Shipment> queryByNameWithPage(String name, int pageNumber);
	
	void finish(int shipmentID);
	void finish(int shipmentID, String accessCode) throws Exception;
}
