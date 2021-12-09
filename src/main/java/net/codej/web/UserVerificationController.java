/*
 * package net.codej.web;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import net.codej.service.UserService; import
 * net.codej.web.dto.UserRegistrationDto; import
 * net.codej.web.dto.UserVerificationDto;
 * 
 * @Controller
 * 
 * @RequestMapping("/verify") public class UserVerificationController {
 * 
 * private UserService userService; public
 * UserVerificationController(UserService userService) { super();
 * this.userService= userService;
 * 
 * }
 * 
 * @ModelAttribute("user") public UserVerificationDto userVerificationDto() {
 * return new UserVerificationDto();
 * 
 * }
 * 
 * @GetMapping public String showRegistrationForm(UserService userService) {
 * return "verify"; }
 * 
 * @PostMapping public String verifyUserAccount(@ModelAttribute("user")
 * UserVerificationDto verificationDto) { userService.save(verificationDto);
 * return "redirect:/verify?success";
 * 
 * }
 * 
 * }
 */
