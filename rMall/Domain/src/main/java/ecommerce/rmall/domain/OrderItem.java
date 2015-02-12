package ecommerce.rmall.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="T_Mall_OrderItem")
@XmlRootElement (name = "OrderItem")
public class OrderItem {

	@Id
	@GeneratedValue
	@Column(name="ID", nullable=false)
	private long id;
	
	@JoinColumn(name="ProductID")
	@ManyToOne(optional=false)
	private Product product;
	
	@Column
	private int quantity;
	
	@Column
	private float price;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
