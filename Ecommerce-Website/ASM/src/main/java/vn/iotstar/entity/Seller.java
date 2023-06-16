package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="sellers")
@NamedQuery(name="Seller.findAll", query="SELECT s FROM Seller s")

public class Seller implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellId;
	
	private String name;
	
	private int status;
	
	@OneToMany (mappedBy = "seller",cascade = CascadeType.ALL)
	private List<Product> products ;

	@OneToOne (mappedBy = "seller",cascade = CascadeType.ALL)
	private User user ;
	
	public Seller() {
		super();
	}

	public Seller(int sellId, String name, String images, int status, List<Product> products, User user) {
		super();
		this.sellId = sellId;
		this.name = name;
		this.status = status;
		this.products = products;
		this.user = user;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
