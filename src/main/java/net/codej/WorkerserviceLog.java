package net.codej;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
@EnableAutoConfiguration
@Entity
@Table(name="WorkerserviceLog")
public class WorkerserviceLog {
	 
	@Id
	@GeneratedValue
	private int sNo;
	private int workerID;
	private String service;
	public WorkerserviceLog() {
		
	}
	
	public WorkerserviceLog(int sNo, int workerID, String service) {
		this.sNo = sNo;
		this.workerID = workerID;
		this.service = service;
	}



	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public int getWorkerID() {
		return workerID;
	}
	public void setWorkerID(int workerID) {
		this.workerID = workerID;
	}
	public String getService() {
		return service;
	}
	public void setLocation(String service) {
		this.service = service;
	}
	
	
}
