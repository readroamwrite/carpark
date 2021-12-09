package net.codej.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.NewDate;

import net.codej.Booking;
import net.codej.WorkerLocationLog;
import net.codej.repository.BookingRepository;
import net.codej.repository.WorkerLocationLogRepository;

@Service
public class WorkerLocationLogService {

	@Autowired
	private WorkerLocationLogRepository repository;
	@Autowired
	private BookingRepository booking;
	public List<WorkerLocationLog> getWorker(String location, String date, String checkInTime, String checkOutTime)
	{
		NewDate obj= new NewDate(date, checkInTime,checkOutTime);
		List<WorkerLocationLog> work= repository.findByLocation(location);
		List<Booking> book;
		List<WorkerLocationLog> list =new ArrayList<WorkerLocationLog>();
		for(WorkerLocationLog w:work)
		{
			book=booking.findByWorkerIDContainingAndStatus(String.valueOf(w.getWorkerID()),"In Transit");
			boolean check=true;
			for(Booking b:book)
			{
				NewDate temp= new NewDate(b.getDate(), b.getCheckInTime(),b.getCheckOutTime());
				check= NewDate.comparison(temp,obj);
				if(!check)
					break;		
					
			}
			if(check)
			list.add(w);
			
		}
		
		return list;
	}
	public List<WorkerLocationLog> getWorker(String location, String date, String checkInTime, String checkOutTime, int id)
	{
		NewDate obj= new NewDate(date, checkInTime,checkOutTime);
		List<WorkerLocationLog> work= repository.findByLocation(location);
		List<Booking> book;
		List<WorkerLocationLog> list =new ArrayList<WorkerLocationLog>();
		for(WorkerLocationLog w:work)
		{
			book=booking.findByWorkerIDContainingAndStatus(String.valueOf(w.getWorkerID()),"In Transit");
			boolean check=true;
			for(Booking b:book)
			{ 
				if(!(b.getBookRefNum()==id))
				{
				
					NewDate temp= new NewDate(b.getDate(), b.getCheckInTime(),b.getCheckOutTime());
				check= NewDate.comparison(temp,obj);
				if(!check)
					break;	
				}
					
			}
			if(check)
			list.add(w);
			
		}
		
		return list;
	}
	public List<String> getUniqueLocation()
	{
		
		return repository.findDistinctLocation();
	}
	public List<WorkerLocationLog> getLocByWorkerId (int wid) {
		return repository.findByWorkerID(wid);
	}

	public void deleteWorkerLocLog(int id) {
		repository.deleteById(id);
	}

	public int getWorkerIDBySNo(int id) {
		WorkerLocationLog a = repository.getById(id);
		return a.getWorkerID();
	}

	public void saveLocationLog(WorkerLocationLog workerLocationLog) {
		repository.save(workerLocationLog);
	}
	
}
