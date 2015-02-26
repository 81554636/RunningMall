package ecommerce.rmall.ws;

/***
 * 客户信息
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Customer")
public class CustomerDTO {

	private String name;
	private String phone;
	private String Address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
