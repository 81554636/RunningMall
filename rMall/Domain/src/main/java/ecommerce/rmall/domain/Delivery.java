package ecommerce.rmall.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/***
 * 收货信息
 * @author martin
 *
 */

@Entity
@Table(name="T_Mall_Delivery")
@XmlRootElement (name = "Delivery")
public class Delivery {

	@Id
	@GeneratedValue
	@Column(name="ID", nullable=false)
	private long id;
	
	@Column(name="Name", nullable=false)
	private String name;
	
	@Column(name="Addr", nullable=false)
	private String address;
	
	@Column(name="Phone", nullable=false)
	private String phone;
	
	@Column(name="deliverDate", nullable=false, columnDefinition="timestamp default CURRENT_TIMESTAMP")
	private Date deliverDate;
	
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
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
