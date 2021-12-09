package net.codej;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
@EnableAutoConfiguration
@Entity
@Table(name="Worker")
public class Worker {
	 
	@Id
	@GeneratedValue
	private int iD;
	private String firstName;
	private String lastName;
	private String email;
    private float rating;
	private double cummHrs;
	public Worker()
	{
		
	}
	public Worker(int iD, String firstName, String lastName, String email, float rating, double cummHrs) {
		this.iD = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.rating = rating;
		this.cummHrs = cummHrs;
	}
	public int getID() {
		return iD;
	}
	public void setID(int iD) {
		this.iD = iD;
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
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public double getCummHrs() {
		return cummHrs;
	}
	public void setCummHrs(double cummHrs) {
		this.cummHrs = cummHrs;
	}
	
	
	
	
}
