package ecommerce.rmall.ws;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Order")
public class OrderDTO {
	
	private DeliveryDTO delivery;
	private Set<OrderItemDTO> details;
	private CustomerDTO owner;
	
	public DeliveryDTO getDelivery() {
		return delivery;
	}
	public void setDelivery(DeliveryDTO delivery) {
		this.delivery = delivery;
	}
	public Set<OrderItemDTO> getDetails() {
		return details;
	}
	public void setDetails(Set<OrderItemDTO> details) {
		this.details = details;
	}
	public CustomerDTO getOwner() {
		return owner;
	}
	public void setOwner(CustomerDTO owner) {
		this.owner = owner;
	}
}
