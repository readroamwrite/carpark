package net.codej.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Transfer;

import net.codej.service.BookingService;

@Controller
public class UserDashboard {
@Autowired
 private BookingService service;

	@GetMapping("/UserDashboard")
	public ModelAndView homepage(ModelAndView mv )
	{
	
		int userid=1;
		Transfer.setId(userid);
		mv.addObject("userid" ,userid);
		service.updateBookings();
		service.updateBookings();
	 mv.setViewName("UserDashboard");
		return mv;
		
	}
	
}

