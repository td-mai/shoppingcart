package entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="Accounts")
public class Account implements Serializable{
	
	public static final String ROLE_MANAGER = "MANAGER";
	public static final String ROLE_EMPLOYEE = "EMPLOYEE";
	
	private String userName;
	private String password;
	private boolean active;
	private String userRole;
	private String firstName;
	private String lastName;
	private String phone;
	
	@Id
	@Column(name="User_Name", length=20, nullable=false)
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="Password", length=20, nullable=false)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password= password;
	}
	
	@Column(name="Active", length=1, nullable=false)
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Column(name="First_Name", length=50, nullable=true)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;		
	}
	
	@Column(name="Last_Name", length=50, nullable=true)
	public String getLastName() {
		return lastName;
	}
	public void setlastName(String lastName) {
		this.lastName = lastName;		
	}
	@Column(name="User_Role", length=20, nullable=false)
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;		
	}
	
	@Column (name="Phone", length=10, nullable=true)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
	    this.phone = phone;
	}
}
