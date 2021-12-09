package net.parkplaza.security.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import net.codej.Transfer;
import net.codej.model.AuthenticationProvider;
import net.codej.model.User;
import net.codej.service.UserService;
//import net.codej.service.UserServiceImpl;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomOAuth2User oAuth2User= (CustomOAuth2User) authentication.getPrincipal();
		String email=oAuth2User.getEmail();
		String name=oAuth2User.getName();
		//get user object by by email
		User user= userService.loaduserByUsername(email);
		System.out.println(" User Email : "+ email );
		
		if (user==null) 
		{
			userService.createNewUserAfterOAuthLoginSuccess(email,name,AuthenticationProvider.GOOGLE);
		}
		else {//update existing customer
			userService.updateUserAfterOAuthLoginSuccess(user,name,AuthenticationProvider.GOOGLE);
			
			}
		super.onAuthenticationSuccess(request, response, authentication);
		
		
		
	}
	

}
