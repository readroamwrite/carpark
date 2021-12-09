package net.codej.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.NewDate;

import net.codej.Booking;
import net.codej.config.ParkingSlot;
import net.codej.repository.BookingRepository;
import net.codej.repository.ParkingSlotRepository;

@Service
public class ParkingSlotService {
	@Autowired
	private ParkingSlotRepository repository;
	@Autowired
	private BookingRepository booking;
	
	public List<ParkingSlot> getParkingSlot(String location, String date ,String CheckInTime, String CheckOutTime)
	{
		NewDate obj= new NewDate(date, CheckInTime,CheckOutTime);
		List<ParkingSlot> park= repository.findByLocation(location);
		List<Booking> book;
		List<ParkingSlot> list =new ArrayList<ParkingSlot>();
		for(ParkingSlot p:park)
		{
			book=booking.findByParkingSlotAndStatus(p.getParkingSlot(),"In Transit");
			boolean check=true;
			for(Booking b:book)
			{
				NewDate temp= new NewDate(b.getDate(), b.getCheckInTime(),b.getCheckOutTime());
				check= NewDate.comparison(temp,obj);
				if(!check)
					break;		
					
			}
			if(check)
			list.add(p);
			
		}
		
		return list;
	}
	public List<ParkingSlot> getParkingSlot(String location, String date ,String CheckInTime, String CheckOutTime, int id)
	{
		NewDate obj= new NewDate(date, CheckInTime,CheckOutTime);
		List<ParkingSlot> park= repository.findByLocation(location);
		List<Booking> book;
		List<ParkingSlot> list =new ArrayList<ParkingSlot>();
		for(ParkingSlot p:park)
		{
			book=booking.findByParkingSlotAndStatus(p.getParkingSlot(),"In Transit");
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
			list.add(p);
			
		}
		
		return list;
	}
	public List<ParkingSlot> getAllParkingSlots() {
		return repository.findAll();
	}

	public void saveParkingSlot(ParkingSlot parkingSlot) {
		this.repository.save(parkingSlot);
	}

	public ParkingSlot getParkingSlotById(int id) {
		Optional<ParkingSlot> optional = repository.findById(id);
		ParkingSlot parkingSlot = null;
		if(optional.isPresent()) {
			parkingSlot = optional.get();
		} else {
			throw new RuntimeException(" ParkingSlot not found for id" + id);
		}
		return parkingSlot;
	}

	public void deleteParkingSlotById(int id) {
		this.repository.deleteById(id);
	}
}
