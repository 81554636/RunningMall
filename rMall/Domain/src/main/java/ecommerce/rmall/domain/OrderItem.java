package ecommerce.rmall.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "OrderItem")
public class OrderItem {

	private int id;
	private int quantity;
	private float price;
	private Specification spec;
	private Product product;
	
	public Product getProduct() { return product; }
	public void setProduct(Product product) { this.product = product; }
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public Specification getSpec() { return spec; }
	public void setSpec(Specification spec) { this.spec = spec; }

	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }

	public float getPrice() { return price; }
	public void setPrice(float price) { this.price = price; }
}
