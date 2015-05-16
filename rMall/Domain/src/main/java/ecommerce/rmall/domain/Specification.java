package ecommerce.rmall.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement (name = "Specification")
public class Specification {
	
	private int id;
	private String name;
	private float price;
	private int max;
	private int min;
	private transient Product product;
	
	@JsonIgnore
	public Product getProduct() { return product; }
	public void setProduct(Product product) { this.product = product; }

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public float getPrice() { return price; }
	public void setPrice(float price) { this.price = price; }
	
	public int getMax() { return max; }
	public void setMax(int max) { this.max = max; }
	
	public int getMin() { return min; }
	public void setMin(int min) { this.min = min; }
}
