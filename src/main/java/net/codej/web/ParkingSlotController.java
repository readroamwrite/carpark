package net.codej.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.NewDate;
import com.example.demo.Temporary;
import com.example.demo.Transfer;

import net.codej.Booking;
import net.codej.TUser;
import net.codej.Worker;
import net.codej.WorkerLocationLog;
import net.codej.WorkerserviceLog;
import net.codej.config.ParkingSlot;
import net.codej.service.BookingService;
import net.codej.service.ParkingSlotService;
import net.codej.service.TUserService;
import net.codej.service.WorkerLocationLogService;
import net.codej.service.WorkerService;
import net.codej.service.WorkerserviceLogService;

@Controller
public class ParkingSlotController {
	
	@Autowired
	private ParkingSlotService service;
	@Autowired
	private WorkerLocationLogService wll;
	@Autowired
	private WorkerserviceLogService wss;
	@Autowired
	private WorkerService w;
	@Autowired
	private BookingService b;
	@Autowired
	private TUserService user;
	@GetMapping("/displayParkingSlots")
	
	public ModelAndView displayParkingSlots(@ModelAttribute Booking book ,ModelAndView mv)
	{
		b.updateBookings();
		int temp=0;
		Transfer.setBooking(book);
		String location = book.getLocation();
		String date = book.getDate();
		System.out.println(date);
		String checkInTime = book.getCheckInTime();
		String  checkOutTime = book.getCheckOutTime();
		int [] cit =NewDate.getTime(checkInTime);
		int [] cot =NewDate.getTime(checkOutTime);
		int time =cot[0] -cit[0];
		double price = 25*time;
		List <String> locationList = wll.getUniqueLocation();
		for(String s: locationList)
		{
			if(s.equals(location))
				{
					temp=1;
					break;
				}
		}
		if(NewDate.error(new NewDate(date,checkInTime ,checkOutTime)))
			mv.setViewName("InvalidDateAndTime");
		else if(temp==0) 
			mv.setViewName("InvalidLocation");
		else
		{
			
}
			List <ParkingSlot> parkingList=service.getParkingSlot(location,date,checkInTime,checkOutTime);
			if(parkingList.isEmpty())
				mv.setViewName("EnrollInWaitList");
			else
			{
	
				
				List <WorkerLocationLog> workLocationList = wll.getWorker(location,date,checkInTime,checkOutTime);
				List <WorkerserviceLog> workserviceList = wss.getServiceById(workLocationList);
				List <Worker> workerList = w.getServiceById(workLocationList);
				List<Temporary> addlist= new ArrayList <Temporary>();
				for(WorkerserviceLog i:workserviceList)
				{
						for(Worker j:workerList)
						{
							if(j.getID()==i.getWorkerID())
							{
								Temporary t= new Temporary();
								t.setFirstName(j.getFirstName());
								t.setLastName(j.getLastName());
								t.setRating(j.getRating());
								t.setService(i.getService());
								t.setWorkerId(j.getID());
								addlist.add(t);
							}
							
						}
				
			}
			mv.addObject("worker", addlist);
			mv.addObject("workserviceList", workserviceList);
			mv.addObject("parkingList", parkingList);
			mv.addObject("location", location);
			mv.addObject("checkInTime", checkInTime);
			mv.addObject("checkOutTime", checkOutTime);
			mv.addObject("price", price);
			Transfer.setPrice(price);
		}
			
		
		return mv;
	}
	@GetMapping("/ConfirmBooking")
	public ModelAndView confirmBooking(HttpServletRequest req,ModelAndView mv)
	{
		
		String done = req.getParameter("done");
		if(done!=null)
		{
			user.saveUser(Transfer.getUser());
			b.saveBooking(Transfer.getBooking());
			mv.setViewName("ConfirmBooking");
		}
		else
		{
			
		
		String parkingSlot = req.getParameter("parkingSlot");
		List <String> ServiceList = wss.getUniqueService();
		String worker="";
		String service="";
		double price=0;
		for(String s:ServiceList)
		{
			String workerID = req.getParameter(s);
			if(workerID!=null)
			{
				if(workerID!="")
				worker= worker +workerID+",";
				service= service + s+ ",";
				price= price + 10;
			}

			
		}
		double total =Transfer.getPrice()+price +100;
		(Transfer.getBooking()).setWorkerID(worker);
		(Transfer.getBooking()).setService(service);
		(Transfer.getBooking()).setParkingSlot(parkingSlot);
		(Transfer.getBooking()).setUserID(Transfer.getId());
		(Transfer.getBooking()).setBillCost(total-100);
		(Transfer.getBooking()).setStatus("In Transit");
		TUser u= user.viewInfo(Transfer.getId());
		Transfer.setUser(u);
		u.setWallet(u.getWallet()-total);
		mv.addObject("book", Transfer.getBooking());
		mv.setViewName("BookingsBill");
		}

		return mv;
	}
	@GetMapping("searchParkingSlots")
	public ModelAndView searchParkingSlots(ModelAndView mv)
		{
			mv.setViewName("searchParkingSlots");
			return mv;
		}
	@GetMapping("Admin/ParkingSlots")
	public String viewParkingSlots(Model model) {
		model.addAttribute("listParkingSlots",service.getAllParkingSlots());
		return "parkingindex";
	}

	@GetMapping("/Admin/showNewParkingSlotForm")
	public String showNewParkingSlotForm(Model model) {
		ParkingSlot parkingSlot = new ParkingSlot();
		model.addAttribute("parkingslot1", parkingSlot);
		return "new_parkingslot";
	}

	@PostMapping("/saveParkingSlot")
	public String saveParkingSlot(@ModelAttribute("parkingslot1") ParkingSlot parkingSlot) {
		service.saveParkingSlot(parkingSlot);
		return "redirect:/Admin/ParkingSlots";
	}

	@GetMapping("/Admin/showFormForParkingSlotUpdate/{id}")
	public String showFormForParkingSlotUpdate(@PathVariable(value = "id") int id, Model model) {

		ParkingSlot parkingSlot = service.getParkingSlotById(id);

		model.addAttribute("parkingslot1", parkingSlot);
		return "update_parkingslot";

	}

	@GetMapping("/Admin/deleteParkingSlot/{id}")
	public String deleteParkingSlot(@PathVariable (value = "id") int id){
		service.deleteParkingSlotById(id);
		return "redirect:/Admin/ParkingSlots";
	}


}
