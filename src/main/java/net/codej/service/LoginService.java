package net.codej.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codej.repository.LoginRepository;

@Service

public class LoginService {
	@Autowired
	private LoginRepository repository;

}
