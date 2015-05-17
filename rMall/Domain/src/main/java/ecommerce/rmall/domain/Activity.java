package ecommerce.rmall.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Activity {

	private int id;
	private String description;
	private boolean isValid;
	private String imgUrl;
	private Product product;
	
	@JsonIgnore
	public boolean isValid() { return isValid; }
	public void setValid(boolean isValid) { this.isValid = isValid; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getImgUrl() { return imgUrl; }
	public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
	
	public Product getProduct() { return product; }
	public void setProduct(Product product) { this.product = product; }
}
