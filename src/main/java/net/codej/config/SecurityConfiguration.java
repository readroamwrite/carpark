package net.codej.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.codej.service.UserService;
import net.codej.service.UserServices;
import net.parkplaza.security.oauth.CustomOAuth2UserService;
import net.parkplaza.security.oauth.OAuth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserServices userServices;
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public CustomOAuth2UserService passwordEncode() {
		return new CustomOAuth2UserService();
		
	}
	@Bean
	public OAuth2LoginSuccessHandler passwordEncod() {
		return new  OAuth2LoginSuccessHandler();
		
	}
	@Bean
	public UserServices passwordEnco() {
		return new  UserService();
		
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService((UserDetailsService)userServices);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
		
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/oauth2/","/verify**","/registration**","/js/**","/css/**","/img/**").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().oauth2Login().loginPage("/login").userInfoEndpoint().userService(oAuth2UserService).and().successHandler(oAuth2LoginSuccessHandler).and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
		//http.authorizeRequests().antMatchers("/verify**","/registration**","/js/**","/css/**","/img/**").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
		
		//http.authorizeRequests().antMatchers("/verify**","/registration**","/js/**","/css/**","/img/**").permitAll().anyRequest().authenticated().and().addFilterBefore(beforeLoginFilter,BeforeAuthenticationFilter.class).formLogin().loginPage("/login").usernameParameter("email").successHandler(loginSuccessHandler).failureHandler(loginFailureHandler).permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
		
	}
	@Autowired
	private CustomOAuth2UserService oAuth2UserService;
	
	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	
	/*
	 * @Autowired private BeforeAuthenticationFilter beforeLoginFilter;
	 * 
	 * @Autowired private LoginSuccessHandler loginSuccessHandler;
	 * 
	 * @Autowired private LoginFailureHandler loginFailureHandler;
	 * 
	 * @Bean(name=BeanIds.Authentication_Manager)
	 * 
	 * @Override public AuthenticationManager authenticationManagerBean() throws
	 * Exception{ return super.authenticationManagerBean(); }
	 */
	
}
