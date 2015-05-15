package ecommerce.rmall.domain;

/***
 * 客户信息
 */
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement (name = "Customer")
public class Customer {

	private int id;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String imgUrl;

	private Date createDate;
	@JsonIgnore private Date lastUpdate;
	@JsonIgnore private String lastUpdateBy;

	private Credential credential;
	public Credential getCredential() { return credential; }
	public void setCredential(Credential credential) { this.credential = credential; }
	
	private boolean isJoinActivity;
	public boolean isJoinActivity() { return isJoinActivity; }
	public void setJoinActivity(boolean isJoinActivity) { this.isJoinActivity = isJoinActivity; }
	
	private boolean isActive;
	public boolean isActive() { return isActive; }
	public void setActive(boolean isActive) { this.isActive = isActive; }
	
	@JsonIgnore private String activateCode;
	public String getActivateCode() { return activateCode; }
	public void setActivateCode(String activateCode) { this.activateCode = activateCode; }
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }

	public Date getCreateDate() { return createDate; }
	public void setCreateDate(Date createDate) { this.createDate = createDate; }

	public Date getLastUpdate() { return lastUpdate; }
	public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

	public String getLastUpdateBy() { return lastUpdateBy; }
	public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }
	
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getImgUrl() { return imgUrl; }
	public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
}
