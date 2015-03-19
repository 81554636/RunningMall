package ecommerce.rmall.domain;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Order")
public class Order{

	private int id;
	private String status;
	private Date createDate;
	private Date lastUpdate;
	private String lastUpdateBy;
	private Customer customer;
	private Delivery delivery;
	private List<OrderItem> details;
	private Shipment shipment;
	private String accessCode;
	
	private OrderStatus orderStatus;
	public void setOrderStatus(OrderStatus status){
		this.orderStatus = status;
	}
	public OrderStatus getOrderStatus(){
		return this.orderStatus;
	}
	
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public List<OrderItem> getDetails() {
		return details;
	}
	public void setDetails(List<OrderItem> details) {
		this.details = details;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
}
