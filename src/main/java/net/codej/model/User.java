package net.codej.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 20)
	private String firstName;
	@Column(nullable = false, length = 20)
	private String lastName;
	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64) // encoded pswrd
	private String password;
	/*
	 * @Column private String oneTimePassword;
	 * 
	 * @Column private Date otpRequestedTime;
	 */
	@Column(nullable = false, unique = true, length = 10)
	private String mobileNum;
	@Column(nullable = false, unique = true, length = 10)
	private String carRegNum;
	@Column(nullable = false, unique = true, length = 20)
	double wallet;
	
	  @Column
	 private String request;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> role;
	
	@Enumerated(EnumType.STRING)
	@Column
	private AuthenticationProvider authProvider;
	public User() {
	}

	/*
	 * String mobileNum, String carRegNum, int wallet, String request
	 */
	public User(String firstName, String lastName, String email, String password, Collection<Role> role,String mobileNum, String carRegNum, int wallet) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		  this.mobileNum = mobileNum; 
		  this.carRegNum = carRegNum; 
		  this.wallet = wallet;
		 // this.request = request;
		 
		this.role = role;
		
	}

	/*
	 * public String getOneTimePassword() { return oneTimePassword; }
	 * 
	 * public void setOneTimePassword(String oneTimePassword) { this.oneTimePassword
	 * = oneTimePassword; }
	 * 
	 * public Date getOtpRequestedTime() { return otpRequestedTime; }
	 * 
	 * public void setOtpRequestedTime(Date otpRequestedTime) {
	 * this.otpRequestedTime = otpRequestedTime; }
	 */

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getCarRegNum() {
		return carRegNum;
	}

	public void setCarRegNum(String carRegNum) {
		this.carRegNum = carRegNum;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	/*
	 * public String getRequest() { return request; }
	 * 
	 * public void setRequest(String request) { this.request = request; }
	 */

	public long getId() {
		return id;
	}

	public Collection<Role> getRole() {
		return role;
	}

	public void setRole(Collection<Role> role) {
		this.role = role;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	/*
	 * public String getMobileNum() { return mobileNum; } public void
	 * setMobileNum(String mobileNum) { this.mobileNum = mobileNum; } public String
	 * getCarRegNum() { return carRegNum; } public void setCarRegNum(String
	 * carRegNum) { this.carRegNum = carRegNum; } public int getWallet() { return
	 * wallet; } public void setWallet(int wallet) { this.wallet = wallet; }
	 */
	  
	  public String getRequest() { return request; }
	  public void setRequest(String request) { this.request = request; }
	 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(AuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}


	
	
	/*
	 * public boolean isOTPReqd() { if(this.getOneTimePassword()==null) {return
	 * false;}
	 * 
	 * long currentTime= System.currentTimeMillis(); long
	 * otpRequestedTime=this.otpRequestedTime.getTime();
	 * if(otpRequestedTime+(300000)< currentTime) {return false;} return true; }
	 */

	
	 

}
