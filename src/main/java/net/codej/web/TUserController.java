package net.codej.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Transfer;

import net.codej.TUser;
import net.codej.service.TUserService;

@Controller
public class TUserController {

	@Autowired
	private TUserService service;
	@GetMapping("/addMoney")
	
	public ModelAndView AddAmount(ModelAndView mv)
	{
		mv.setViewName("addMoney");
		return mv;
		
	}
	@PostMapping("/MoneyAdded")
	public ModelAndView DebitAmount(@RequestParam(name="amount") double amount ,ModelAndView mv)
	{
		service.addMoney(amount, Transfer.getId());
		mv.setViewName("MoneyAdded");
		return mv;
	}
	@PutMapping("/UserUpdate")
	public TUser update(@RequestBody TUser p)
	{
		return service.modiflyDetails(p);
	}
   @GetMapping("/viewUserInfo")
	
	public ModelAndView viewUserInfo(ModelAndView mv ,HttpServletRequest req)
	{
	   
	   String carRegNum = req.getParameter("carRegNum");
	   String mobileNum = req.getParameter("mobileNum");
	   String email = req.getParameter("email");
	   
	   TUser user= service.viewInfo(Transfer.getId());
	   if(carRegNum!=null)
	   user.setCarRegNum(carRegNum);
	   if(email!=null)
	   user.setEmail(email);
	   if(mobileNum!=null)
	   user.setMobileNum(mobileNum);
	   service.saveUser(user);
	   mv.addObject("user", user);
		mv.setViewName("viewUserInfo");
		return mv;
		
	}
}
