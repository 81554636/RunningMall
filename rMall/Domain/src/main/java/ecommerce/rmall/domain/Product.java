package ecommerce.rmall.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="T_Mall_Product")
@XmlRootElement (name = "Product")
public class Product {
	
	@Id
	@GeneratedValue
	@Column(name="ID", nullable = false)
	private int id;

	@Column(name="name", nullable = false)
	private String displayName;
	
	@Column(name="price", nullable = false)
	private float price;
	
	@Column(name = "CreateDate", nullable = false, columnDefinition="timestamp default CURRENT_TIMESTAMP")
	private Date createDate;
	
	@Column(name = "UpdateDate", nullable = false, columnDefinition="timestamp")
	private Date lastUpdate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
