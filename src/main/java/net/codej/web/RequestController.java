package net.codej.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Transfer;

import net.codej.Request;
import net.codej.service.BookingService;
import net.codej.service.RequestService;

@Controller
public class RequestController {
	@Autowired
	private RequestService service;
	@Autowired
	private BookingService booking;
	@PostMapping("/addRequest")
	public Request addRequest(@RequestBody Request p)
	{
		return service.saveRequest(p);
	}
	@GetMapping("/viewRequest")
	public ModelAndView viewAllRequests(ModelAndView mv)
	{
		booking.updateBookings();
		booking.updateBookings();
		List <Request> booking =service.getRequest(Transfer.getId());
		mv.addObject("booking", booking);
		mv.setViewName("viewRequest");
		
		return mv;
	}
	@DeleteMapping("/deleteRequest/{id}")
	public String deleteProduct(@PathVariable int id)
	{
		return service.deleteRequest(id);
	}
	@GetMapping("/Admin/RequestApproval")
	public String requestAdminView(Model model){
		model.addAttribute("listRequests",service.getRequestByStatus("In Transit"));
		return "reqindex";
	}

	@GetMapping("/Admin/ModifyRequestStatus/Approve/{id}")
	public String approvalRequest(@PathVariable (value = "id") int bookRefNum){
		service.requestApproval(bookRefNum, "Approved");
		return "redirect:/Admin/RequestApproval";
	}
}
