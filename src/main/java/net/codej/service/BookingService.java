package net.codej.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.NewDate;

import net.codej.Booking;
import net.codej.Request;
import net.codej.TUser;
import net.codej.WaitList;
import net.codej.config.ParkingSlot;
import net.codej.repository.BookingRepository;


@Service

public class BookingService {
	@Autowired
	private BookingRepository repository;
	@Autowired
	private TUserService user;
	@Autowired
	private WaitListService waitlist;
	@Autowired
	private ParkingSlotService parking;
	@Autowired
	private RequestService request;

	public Booking saveBooking(Booking b)
	{
		return repository.save(b);
		
	}
	public List<Booking> getBooking(int userID)
	{
		return repository.findByUserID(userID);
		
	}
	public Booking getBookingByID(int id)
	{
			return repository.findById(id).orElse(null);
			
		
	}
	
	public List <Booking> findBookingByStatus(int id)
	{
		List <Booking >b =  repository.findByUserIDAndStatus(id,"In Transit");
	
		return b;
	}
	public void updateBookings()
	{
		List <Booking> book =  repository.findByStatus("In Transit");
		List <WaitList> wait =  waitlist.getWaitListByStatus("In Transit");
		List <Request> red =  request.getRequestByStatus("In Transit");
		List <Request> req =  request.getRequestByStatus("Approved");

		for(Booking b:book)
		{
			NewDate temp= new NewDate(b.getDate(),b.getCheckInTime(),b.getCheckOutTime());
			if(NewDate.update(temp))
				{
					b.setStatus("Completed");
					TUser u= user.viewInfo(b.getUserID());
					u.setWallet(u.getWallet()+ 100);
					user.saveUser(u);
					repository.save(b);
				}
			
		}
		for(Request r:req)
		{
		
					Booking b= repository.findById(r.getBookRefNum()).orElse(null);
					TUser u= user.viewInfo(b.getUserID());
					u.setWallet(u.getWallet()+ b.getBillCost()-r.getBillCost());
					b.setBillCost(r.getBillCost());
					b.setService(r.getService());
					b.setWorkerID(r.getWorkerID());
					user.saveUser(u);
					repository.save(b);
				
			
		}
		for(Request r:red)
		{
			NewDate temp= new NewDate(r.getDate(),r.getCheckInTime(),r.getCheckOutTime());
			if(NewDate.update(temp))
				{
					r.setStatus("Expired");
					request.saveRequest(r);
				}
			
		}
		red =  request.getRequestByStatus("In Transit");
		for(Request r:red)
		{
			int t=0;
			List <ParkingSlot> list =parking.getParkingSlot(r.getLocation(), r.getDate(), r.getCheckInTime(), r.getCheckOutTime());
			if(!list.isEmpty())
			{
				for(ParkingSlot p:list)
				{
					if(p.getParkingSlot().equals(r.getParkingSlot()))
					{
						t=1;
						break;
					}
				}
			}
			
			if(t==0)
			{
				r.setStatus("Declined");
				request.saveRequest(r);
			}
			
		}
		
		for(WaitList w:wait)
		{
			NewDate temp= new NewDate(w.getDate(),w.getCheckInTime(),w.getCheckOutTime());
			if(NewDate.update(temp))
				{
					w.setStatus("Expired");
					waitlist.saveWaitList(w);
				}
			
		}
		wait =waitlist.getWaitListByStatus("In Transit");
			for(WaitList w:wait)
			{
				List <ParkingSlot> list =parking.getParkingSlot(w.getLocation(), w.getDate(), w.getCheckInTime(), w.getCheckOutTime());
				if(list!=null)
					{
							int [] cit =NewDate.getTime(w.getCheckInTime());
							int [] cot =NewDate.getTime(w.getCheckOutTime());
							int time =cot[0] -cit[0];
							ParkingSlot p= new ParkingSlot();
							for(ParkingSlot k:list)
							{
								p=k;
								break;
							}
							double price = 25*time;
					     Booking b =  new Booking(w.getUserID(), p.getParkingSlot(), w.getLocation(), w.getDate(), w.getCheckInTime(), w.getCheckOutTime(), price, "In Transit");
					     TUser u= user.viewInfo(b.getUserID());
					     w.setStatus("Assigned");
							u.setWallet(u.getWallet()- 100 - price);
							repository.save(b);
						user.saveUser(u);
					     waitlist.saveWaitList(w);
					}
				
			}
			
		

	}
	
}
