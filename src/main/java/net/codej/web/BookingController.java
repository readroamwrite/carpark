package net.codej.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.NewDate;
import com.example.demo.Transfer;

import net.codej.Booking;
import net.codej.Request;
import net.codej.TUser;
import net.codej.WorkerLocationLog;
import net.codej.WorkerserviceLog;
import net.codej.config.ParkingSlot;
import net.codej.service.BookingService;
import net.codej.service.ParkingSlotService;
import net.codej.service.RequestService;
import net.codej.service.TUserService;
import net.codej.service.WorkerLocationLogService;
import net.codej.service.WorkerserviceLogService;



@Controller
public class BookingController {
	
	@Autowired
	private BookingService service;
	@Autowired
	private TUserService user;
	@Autowired
	private WorkerserviceLogService wss;
	@Autowired
	private ParkingSlotService parking;
	@Autowired
	private WorkerLocationLogService wll;
	@Autowired
	private RequestService rest;
	@PostMapping("/addBooking")
	public Booking addBooking(@RequestBody Booking p)
	{
		service.updateBookings();
		service.updateBookings();
		return service.saveBooking(p);
	}
	@GetMapping("/viewBookings")
	public ModelAndView viewAllBookings(ModelAndView mv)
	{
		service.updateBookings();
		service.updateBookings();
		List<Booking> booking =service.getBooking(Transfer.getId());
		mv.addObject("booking", booking);
		mv.setViewName("viewBookings");
		return mv;
	}
	@GetMapping("/modiflyUserBooking")
	public ModelAndView modiflyBooking(ModelAndView mv )
	
	{
		service.updateBookings();
		service.updateBookings();
		List <Booking> booking =service.findBookingByStatus(Transfer.getId());
		List <Booking> btemp= new ArrayList<Booking>();
		for(Booking b:booking)
		{
			Request request =rest.getRequestByRef(b.getBookRefNum());
			if(request==null)
			btemp.add(b);
		}
		
		mv.addObject("booking", btemp);
		mv.setViewName("modiflyUserBooking");
		return mv;
	}
	@PostMapping("/bookingModifly")
	public ModelAndView modiflyTransitBooking(@RequestParam(name="BookRefNum") int bookRefNum , ModelAndView mv)
	{
		 Booking booking =service.getBookingByID(bookRefNum);
		 List <String> ServiceList = wss.getUniqueService();
		 List <String> ServicesToBeOffered= new ArrayList <String>();
		 double c = booking.getBillCost() -(25*(NewDate.getTime(booking.getCheckOutTime())[0] - NewDate.getTime(booking.getCheckInTime())[0]));
		 if(!NewDate.modified(new NewDate(booking.getDate(),booking.getCheckInTime(),booking.getCheckOutTime())))
			 mv.setViewName("LateModifly");
		 else
		 {
			 for(String s:ServiceList)
				{
				 if(booking.getService()!=null)
				 {
					 if((booking.getService()).contains(s))
						 continue;
					 ServicesToBeOffered.add(s);
						 
							
				 }
				 else
					 ServicesToBeOffered.add(s);			
				}
	
			 mv.addObject("booking", booking);
			 mv.addObject("c", c);
			 mv.addObject("service", ServicesToBeOffered);
			 mv.setViewName("bookingModifly");
		 }
		 
		return  mv;
	}
	@GetMapping("/modiflyCheck")
	public ModelAndView modiflyTransitBooking(HttpServletRequest req,ModelAndView mv)
	{
		
		String location=req.getParameter("location");
		String date=req.getParameter("date");
		String checkInTime=req.getParameter("checkInTime");
		String checkOutTime=req.getParameter("checkOutTime");
		String bookRefNum=req.getParameter("bookRefNum");
		String userID=req.getParameter("userID");
		String parkingSlot=req.getParameter("parkingSlot");
		String workerID=req.getParameter("workerID");
		String service=req.getParameter("service");
		Double c=Double.parseDouble(req.getParameter("c"));
		List <String> ServiceList = wss.getUniqueService();	
		List<ParkingSlot> park =parking.getParkingSlot(location, date ,checkInTime, checkOutTime, Integer.parseInt(bookRefNum));
		List <WorkerLocationLog> workLocationList = wll.getWorker(location,date,checkInTime,checkOutTime,Integer.parseInt(bookRefNum));
		List <WorkerserviceLog> workserviceList = wss.getServiceById(workLocationList);
		double total=25* (NewDate.getTime(checkOutTime)[0] - NewDate.getTime(checkInTime)[0]);
		int temp;	
		if(NewDate.team(checkInTime,checkOutTime))
			temp=2;
		else
		{
			temp=0;
			
			for(ParkingSlot p: park)
			{
				if(p.getParkingSlot().equals(parkingSlot))
					{
						temp=1;
						break;
					}
			}
		}

		if(temp==1)
		{
				
			for(String s:ServiceList)
			{
				String t=req.getParameter(s);
				System.out.println("\n"+ t + " ");
				if(t!=null)
				{
					
					for(WorkerserviceLog w:workserviceList)
					{
						temp=0;
						if(w.getService().equals(t))
						{
	
							service= service +t +",";
							System.out.println(service+ " ");
							workerID= workerID + w.getWorkerID() +",";
							total += 10;
							temp=1;
							break;
						}
					}
					
				}
			
					
			}
		}
		if(temp==1)
		{
			total=total+c;
			Request t= new Request(Integer.parseInt(bookRefNum),Integer.parseInt(userID),workerID,parkingSlot,location,date,checkInTime,checkOutTime,service,total,"In Transit");
			rest.saveRequest(t);
		}
		
		
		mv.addObject("temp", temp);
		mv.setViewName("modiflyCheck");
		return  mv;
	}
	@GetMapping("/cancelBooking")
	public ModelAndView cancelBooking(ModelAndView mv )
	
	{
		service.updateBookings();
		List <Booking> booking =service.findBookingByStatus(Transfer.getId());
		mv.addObject("booking", booking);
		mv.setViewName("cancelBooking");
		return mv;
	}

	@PostMapping("/bookingCancelled")
	public ModelAndView bookingCancelled(@RequestParam(name="BookRefNum") int bookRefNum ,ModelAndView mv )
	{
		Booking b= service.getBookingByID(bookRefNum);
		b.setStatus("Cancelled");
		TUser u= user.viewInfo(b.getUserID());
		double cost= b.getBillCost();
		boolean t= NewDate.cancelled(new NewDate(b.getDate(),b.getCheckInTime(),b.getCheckOutTime()));
		if(t)
		{
			double kj =u.getWallet()+ cost +100;
			u.setWallet(kj);
			b.setBillCost(0.0);
		}
		else
		{
			double kj =u.getWallet()+ cost;
			u.setWallet(kj);
			b.setBillCost(100.0);
		}
		user.saveUser(u);
		service.saveBooking(b);
		mv.addObject("cost", cost);
		mv.addObject("t", t);
		mv.setViewName("BookingCancelled");
		return mv;
	}
	
}
