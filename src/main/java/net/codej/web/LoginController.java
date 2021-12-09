package net.codej.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.codej.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService service;
}
