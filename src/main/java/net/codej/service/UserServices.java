


//UserService interface impl by UserServiceImpl

 package net.codej.service;
 
 import org.springframework.security.core.userdetails.UserDetails; import
 org.springframework.security.core.userdetails.UserDetailsService; import
 org.springframework.security.core.userdetails.UsernameNotFoundException;
 
 import net.codej.model.User; import net.codej.web.dto.UserRegistrationDto;
 
 public interface UserServices extends UserDetailsService {
 
 User save(UserRegistrationDto registrationDto );
 
 UserDetails loadUserByUsername(String username) throws
 UsernameNotFoundException;
 
 
	/*
	 * public void generateOTP(User user) ; public void sendOTPEmail(User user,
	 * String OTP); public void clearOTP(User user);
	 */
 
 }
