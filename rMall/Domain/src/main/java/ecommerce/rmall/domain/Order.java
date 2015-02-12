package ecommerce.rmall.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="T_Mall_Order")
@XmlRootElement (name = "Order")
public class Order {
	
	@Id
	@GeneratedValue
	@Column(name="ID", nullable=false)
	private long id;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="CreateDate", nullable=false, columnDefinition="timestamp default CURRENT_TIMESTAMP")
	private Date createDate;
	
	@Column(name="UpdateDate", nullable=false, columnDefinition="timestamp")
	private Date lastUpdate;
	
	@Column(name="UpdateBy", nullable=true)
	private String lastUpdateBy;
	
	@JoinColumn(name="CustomerID")
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private Customer customer;
	
	@JoinColumn(name="DeliveryID")
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private Delivery delivery;

	@JoinColumn(name="OrderID",nullable=false)
	@OneToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.EAGER)
	private List<OrderItem> details;

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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
