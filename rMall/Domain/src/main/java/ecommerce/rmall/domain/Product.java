package ecommerce.rmall.domain;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement (name = "Product")
public class Product {
	
	private int id;
	private String displayName;
	private String description;
	private float price;
	private int max;
	private int min;
	private String url;
	private List<String> imgUrls;

	@JsonIgnore private boolean isValid;
	@JsonIgnore private Date createDate;
	@JsonIgnore private Date lastUpdate;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getDisplayName() { return displayName; }
	public void setDisplayName(String displayName) { this.displayName = displayName; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }
	
	public List<String> getImgUrls() { return imgUrls; }
	public void setImgUrls(List<String> imgUrls) { this.imgUrls = imgUrls; }

	public float getPrice() { return price; }
	public void setPrice(float price) { this.price = price; }

	public Date getCreateDate() { return createDate; }
	public void setCreateDate(Date createDate) { this.createDate = createDate; }

	public Date getLastUpdate() { return lastUpdate; }
	public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }
	
	public int getMax() { return max; }
	public void setMax(int max) { this.max = max; }
	
	public int getMin() { return min; }
	public void setMin(int min) { this.min = min; }
	
	public boolean isValid() { return isValid; }
	public void setValid(boolean isValid) { this.isValid = isValid; }
}
