package net.codej.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Transfer;

import net.codej.WaitList;
import net.codej.service.WaitListService;

@Controller
public class WaitListController {

	@Autowired
	private WaitListService service;
	@GetMapping("WaitListConfirm")
	public ModelAndView WaitlistConfirm(ModelAndView mv)
		{
			WaitList wait = new WaitList(Transfer.getId(),
					(Transfer.getBooking()).getLocation(), 
					(Transfer.getBooking()).getCheckInTime(),
					(Transfer.getBooking()).getCheckOutTime(),
					(Transfer.getBooking()).getDate(),"In Transit");
			service.saveWaitList(wait);
			mv.setViewName("WaitListConfirm");
			return mv;
		}
	@GetMapping("WaitList")
	public ModelAndView Waitlist(ModelAndView mv)
		{
			List <WaitList> wait =service.getWaitList(Transfer.getId());
			mv.addObject("wait", wait);
			mv.setViewName("WaitList");
			return mv;
		}
}
