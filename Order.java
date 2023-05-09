import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="\"order\"")		//quotes are needed around order since order is an SQL keyword
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="number")
	private int number;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="item")
	private String item;
	
	@Column(name="price")
	private double price;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	public Order() {
		
	}
	
	public Order(LocalDate date, String item, double price) {
		this.date = date;
		this.item = item;
		this.price = price;
	}

	public Order(LocalDate date, String item, double price, Customer customer) {
		this.date = date;
		this.item = item;
		this.price = price;
		this.customer = customer;
	}

	public int getNumber() {
		return number;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [number=" + number + ", date=" + date + ", item=" + item + ", price=" + price + "]";
	}
	
	
	
}
