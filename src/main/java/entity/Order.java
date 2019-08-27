package entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Orders", uniqueConstraints= {@UniqueConstraint(columnNames= "Order_Num")})
public class Order implements Serializable{

	private String id;
	private Date orderDate;
	private int orderNum;
	private double amount;
	
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	private String customerEmail;
	
	@Id
	@Column(name="ID", length=50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="Order_Num", nullable=false)
	public int getOderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
		
	}
	
	@Column(name="Order_Date", nullable=false)
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setorderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Column(name="Amount", nullable=false)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name="Customer_Name", nullable=false)
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
