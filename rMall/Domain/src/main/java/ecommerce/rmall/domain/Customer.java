package ecommerce.rmall.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_Mall_Customer")
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name="ID", nullable = false)
	private long id;
	
	@Column(name="Name", nullable = false)
	private String name;
	
	@Column(name="Addr")
	private String Address;
	
	@Column(name = "CreateDate", nullable = false, columnDefinition="timestamp default CURRENT_TIMESTAMP")
	private Date createDate;
	
	@Column(name = "UpdateDate", nullable = false, columnDefinition="timestamp")
	private Date lastUpdate;
	
	@Column(name = "UpdateBy", nullable = true)
	private String lastUpdateBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
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

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
}
