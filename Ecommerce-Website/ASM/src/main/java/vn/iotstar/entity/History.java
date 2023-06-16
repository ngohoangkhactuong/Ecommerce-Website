package vn.iotstar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="histories")
@NamedQuery(name="History.findAll", query="SELECT h FROM History h")
public class History implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="order_date")
	private String orderDate;

	@Column(name="shipping_address")
	private String shippingAddress;
	
	@Column(name="total_price")
	private double price;
	
	@Column(name="user_email")
	private String user_email;
	
	@Column(name="status")
	private int status;
	
	@OneToMany(mappedBy="history",cascade = CascadeType.ALL)
	private List<HistoryDetail> historyDetails =new ArrayList<HistoryDetail>();



	public History() {
		super();
	}
	
	
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<HistoryDetail> getHistoryDetails() {
		return historyDetails;
	}

	public void setHistoryDetails(List<HistoryDetail> historyDetails) {
		this.historyDetails = historyDetails;
	}

	
	
	

	
}
