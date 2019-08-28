package entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
@Entity
@Table(name="Products")
public class Product implements Serializable{

	private String code;
	private String name;
	private Date createdDate;
	private double price;
	private byte[] image;
	
	public Product() {}
	
	@Id
	@Column(name="Code", length=20, nullable=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code =code;
	}
	
	@Column(name="Name", length=255, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Created_Date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Lob
	@Column(name="Image", length=Integer.MAX_VALUE, nullable=true)
	public byte[] getInmage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
