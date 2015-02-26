package ecommerce.rmall.ws;

import javax.xml.bind.annotation.XmlRootElement;

import ecommerce.rmall.domain.OrderItem;

@XmlRootElement (name = "OrderItem")
public class OrderItemDTO {
	private float price;
	private int quantity;
	private ProductDTO product;
	
	public OrderItem convert(){
		OrderItem rtn = new OrderItem();
		rtn.setPrice(this.price);
		rtn.setQuantity(this.quantity);
		rtn.setProduct(this.product.convert());
		return rtn;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
}
