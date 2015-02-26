package ecommerce.rmall.ws;

import javax.xml.bind.annotation.XmlRootElement;

import ecommerce.rmall.domain.Product;

@XmlRootElement (name = "Product")
public class ProductDTO {
	private String displayName;
	private float price;
	private int identity;
	public Product convert(){
		Product rtn = new Product();
		rtn.setId(this.identity);
		rtn.setDisplayName(this.displayName);
		rtn.setPrice(this.price);
		return rtn;
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
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
}
