package ecommerce.rmall.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_Mall_Order")
public class Order {
	
	@Id
	@GeneratedValue
	@Column(name="ID", nullable = false)
	private long id;
	
	@Column(name="OrderDate", nullable = false)
	private Date orderDate;
	
	@Column(name="ShipDate")
	private Date shipDate;
	
	@Column(name="Status")
	private String status;
	
	@Column(name = "LastUpdate", nullable = false)
	private Date lastUpdate;
	
	@Column(name = "LastUpdateBy", nullable = false)
	private String lastUpdateBy;
	
	@JoinColumn(name="CustomerID")
	@ManyToOne(optional=false)
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
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
