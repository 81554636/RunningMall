package ecommerce.rmall.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Coupon {
	
	private int id;
	@JsonIgnore
	private Customer owner;
	private float cutPrice;
	private Date expireTime;
	private Date startTime;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getOwner() {
		return owner;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	public float getCutPrice() {
		return cutPrice;
	}
	public void setCutPrice(float cutPrice) {
		this.cutPrice = cutPrice;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
