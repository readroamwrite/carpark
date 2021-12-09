package net.codej.web.dto;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;

import net.codej.model.Role;

public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Collection<Role> role;
	/*
	 * private String oneTimePassword;
	 * 
	 * private Date otpRequestedTime;
	 */
	  private String mobileNum; 
	  private String carRegNum;
	  int wallet;
	  //private String request;
	 
	
	
	
	
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
	public int getWallet() {
		return wallet;
	}
	public void setWallet(int wallet) {
		this.wallet = wallet;
	}
	public Collection<Role> getRole() {
		return role;
	}
	public void setRole(Collection<Role> role) {
		this.role = role;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public UserRegistrationDto(String firstName, String lastName, String email, String password,Collection<Role> role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNum = mobileNum;
		this.carRegNum = carRegNum;
		this.wallet = wallet;
		//this.request = request;
		this.role = role;
	}
	public UserRegistrationDto() {
		// TODO Auto-generated constructor stub
	}
	
		
}
