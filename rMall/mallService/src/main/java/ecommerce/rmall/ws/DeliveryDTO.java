package ecommerce.rmall.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import ecommerce.rmall.domain.Delivery;

@XmlRootElement (name = "Delivery")
public class DeliveryDTO {
	
	private String address;
	private String receiver;
	private String contact;
	private Date deliveryDate;

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public Delivery convert(){
		Delivery rtn = new Delivery();
		rtn.setAddress(this.address);
		rtn.setDeliverDate(this.deliveryDate);
		rtn.setName(this.receiver);
		rtn.setPhone(this.contact);
		return rtn;
	}
}
