package ecommerce.rmall.domain;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Shipment")
public class Shipment {

	private int id;
	private ShipmentStatus status;
	private Date createDate;
	private Date lastUpdate;
	private String lastUpdateBy;
	private Station station;
	private Delivery delivery;
	private Set<OrderItem> details;
	private String description;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public ShipmentStatus getStatus() { return status; }
	public void setStatus(ShipmentStatus status) { this.status = status; }

	public Date getCreateDate() { return createDate; }
	public void setCreateDate(Date createDate) { this.createDate = createDate; }

	public Date getLastUpdate() { return lastUpdate; }
	public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

	public String getLastUpdateBy() { return lastUpdateBy; }
	public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }

	public Station getStation() { return station; }
	public void setStation(Station station) { this.station = station; }

	public Delivery getDelivery() { return delivery; }
	public void setDelivery(Delivery delivery) { this.delivery = delivery; }

	public Set<OrderItem> getDetails() { return details; }
	public void setDetails(Set<OrderItem> details) { this.details = details; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
}
