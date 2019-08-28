package entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;

@Entity
@Table(name="Order_Details")
public class OrderDetail implements Serializable{

	private String id;
	private Order order;
	private Product product;
	private int quantity;
	private double price;
	private double amount;
	
	@Id
	@Column(name="ID", length=50, nullable=false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id= id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORDER_ID", nullable=false, foreignKey=@ForeignKey(name="ORDER_DETAIL_ORD_FK"))
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID", nullable=false, foreignKey=@ForeignKey(name="ORDER_DETAIL_PRODUCT_FK"))
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		
		this.product = product;
	}
	
	@Column(name="Quantity", nullable= false)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity() {
		this.quantity = quantity;
	}
	
	@Column(name="Price", nullable=false)
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name="Amount", nullable=false)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
