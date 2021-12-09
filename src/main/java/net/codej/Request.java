package net.codej;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
@EnableAutoConfiguration
@Entity
@Table(name="Request")
public class Request {

		@Id
		private int bookRefNum;
		private int userID;
		private String workerID;
		private String parkingSlot;
		private String location;
		private String date;
		private String checkInTime;
		private String checkOutTime;
		private String service;
		private double billCost;
		private String status;
		public Request()
		{}

		public Request(int bookRefNum, int userID, String workerID, String parkingSlot, String location, String date,
				String checkInTime, String checkOutTime, String service, double billCost, String status) {
			this.bookRefNum = bookRefNum;
			this.userID = userID;
			this.workerID = workerID;
			this.parkingSlot = parkingSlot;
			this.location = location;
			this.date = date;
			this.checkInTime = checkInTime;
			this.checkOutTime = checkOutTime;
			this.service = service;
			this.billCost = billCost;
			this.status = status;
		}
		

		public String getWorkerID() {
			return workerID;
		}

		public void setWorkerID(String workerID) {
			this.workerID = workerID;
		}

		public int getBookRefNum() {
			return bookRefNum;
		}
		public void setBookRefNum(int bookRefNum) {
			this.bookRefNum = bookRefNum;
		}
		public int getUserID() {
			return userID;
		}
		public void setUserID(int userID) {
			this.userID = userID;
		}
		public String getParkingSlot() {
			return parkingSlot;
		}
		public void setParkingSlot(String parkingSlot) {
			this.parkingSlot = parkingSlot;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
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
		public String getService() {
			return service;
		}
		public void setService(String service) {
			this.service = service;
		}
		public double getBillCost() {
			return billCost;
		}
		public void setBillCost(double billCost) {
			this.billCost = billCost;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	
		
}
