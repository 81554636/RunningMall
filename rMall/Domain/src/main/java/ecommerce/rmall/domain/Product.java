package ecommerce.rmall.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement (name = "Product")
public class Product {
	
	private int id;
	private String displayName;
	private String description;
	/***
	 * 产地 */
	private String place;
	/***
	 * 详细信息 URL */
	private String url;
	/***
	 * 基础信息的幻灯片地址 */
	private List<String> imgUrls;
	/***
	 * 规格 */
	private Set<Specification> specs;
	/***
	 * 配送地区 */
	private List<String> areas;
	
	private boolean isValid;
	@JsonIgnore private Date createDate;
	@JsonIgnore private Date lastUpdate;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getDisplayName() { return displayName; }
	public void setDisplayName(String displayName) { this.displayName = displayName; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public String getPlace() { return place; }
	public void setPlace(String place) { this.place = place; }
	
	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }
	
	public List<String> getImgUrls() { return imgUrls; }
	public void setImgUrls(List<String> imgUrls) { this.imgUrls = imgUrls; }
	
	public List<String> getAreas() { return areas; }
	public void setAreas(List<String> area) { this.areas = area; }

	public Date getCreateDate() { return createDate; }
	public void setCreateDate(Date createDate) { this.createDate = createDate; }

	public Date getLastUpdate() { return lastUpdate; }
	public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }
	
	@JsonIgnore
	public boolean isValid() { return isValid; }
	public void setValid(boolean isValid) { this.isValid = isValid; }
	
	public Set<Specification> getSpecs() { return specs; }
	public void setSpecs(Set<Specification> specs) { this.specs = specs; }
}
