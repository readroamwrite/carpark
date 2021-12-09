package net.codej;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
@EnableAutoConfiguration
@Entity
@Table(name="WorkerLocationLog")
public class WorkerLocationLog {
	 
	@Id
	@GeneratedValue
	private int sNo;
	private int workerID;
	private String location;
	public WorkerLocationLog() {
		
	}
	
	public WorkerLocationLog(int sNo, int workerID, String location) {
		super();
		this.sNo = sNo;
		this.workerID = workerID;
		this.location = location;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
