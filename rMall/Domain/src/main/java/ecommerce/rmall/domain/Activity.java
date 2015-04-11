package ecommerce.rmall.domain;

import java.util.List;

public class Activity {
	
	private int id;
	private List<Product> details;
	private String description;
	private boolean isValid;
	
	public boolean isValid() { return isValid; }
	public void setValid(boolean isValid) { this.isValid = isValid; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public List<Product> getDetails() { return details; }
	public void setDetails(List<Product> details) { this.details = details; }
}
