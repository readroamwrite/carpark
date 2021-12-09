
package net.codej;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name="WaitList")
public class WaitList {
 
	@Id
	@GeneratedValue
	private int sNo;
	private int userID;
	private String location;
	private String checkInTime;
	private String checkOutTime;
	private String date;
	private String status;
	public WaitList(int sNo, int userID, String location, String checkInTime, String checkOutTime, String date,
			String status) {
		this.sNo = sNo;
		this.userID = userID;
		this.location = location;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.date = date;
		this.status = status;
	}
	public WaitList(int userID, String location, String checkInTime, String checkOutTime, String date, String status) {
		this.userID = userID;
		this.location = location;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.date = date;
		this.status = status;
	}
	
	public WaitList() {
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	
	
	}
	
	
	

