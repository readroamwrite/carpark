/*
 * package net.codej.config;
 * 
 * import java.io.IOException;
 * 
 * import javax.naming.AuthenticationException; import
 * javax.servlet.ServletException; import javax.servlet.http.HttpServletRequest;
 * import javax.servlet.http.HttpServletResponse; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.web.authentication.
 * SimpleUrlAuthenticationFailureHandler; import
 * org.springframework.stereotype.Component;
 * 
 * import net.codej.service.UserService;
 * 
 * @Component public class LoginFailureHandler extends
 * SimpleUrlAuthenticationFailureHandler {
 * 
 * @Autowired private UserService userService;
 * 
 * @Override public void onAuthenticationFailure(HttpServletRequest request,
 * HttpServletResponse response, AuthenticationException exception) throws
 * IOException, ServletException {
 * 
 * String email = request.getParameter("email");
 * System.out.println("onAuthenticationFailure email: " + email);
 * request.setAttribute("email", email);
 * 
 * String redirectURL = "/login?error&email=" + email;
 * 
 * if (exception.getMessage().contains("OTP")) { redirectURL =
 * "/login?otp=true&email=" + email; } else { User user =
 * userService.getuserByEmail(email); if (user.isOTPRequired()) { redirectURL =
 * "/login?otp=true&email=" + email; } }
 * 
 * super.setDefaultFailureUrl(redirectURL);
 * super.onAuthenticationFailure(request, response, exception); }
 * 
 * }
 */