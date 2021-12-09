/*
 * package net.codej.service;
 * 
 * import java.io.UnsupportedEncodingException; import java.util.Arrays; import
 * java.util.Collection; import java.util.Date; import
 * java.util.stream.Collectors;
 * 
 * import javax.mail.MessagingException; import javax.mail.internet.MimeMessage;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.mail.javamail.JavaMailSenderImpl; import
 * org.springframework.mail.javamail.MimeMessageHelper; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.stereotype.Service;
 * 
 * import net.bytebuddy.utility.RandomString; import net.codej.model.Role;
 * import net.codej.model.User; import net.codej.repository.UserRepository;
 * import net.codej.web.dto.UserRegistrationDto;
 * 
 * @Service public class UserServiceImpl implements UserService {
 * 
 * private UserRepository userRepository;
 * 
 * @Autowired JavaMailSenderImpl mailsender;
 * 
 * @Autowired private PasswordEncoder pEncoder;
 * 
 * @Autowired private BCryptPasswordEncoder passwordEncoder;
 * 
 * public UserServiceImpl(UserRepository userRepository) { super();
 * this.userRepository = userRepository; }
 * 
 * @Override public User save(UserRegistrationDto registrationDto) {
 * 
 * User user = new User(registrationDto.getFirstName(),
 * registrationDto.getLastName(),registrationDto.getEmail(),passwordEncoder.
 * encode(registrationDto.getPassword()), Arrays.asList(new
 * Role("ROLE_USER")),registrationDto.getMobileNum(),registrationDto.
 * getCarRegNum(),registrationDto.getWallet()); return
 * userRepository.save(user);
 * 
 * }
 * 
 * public User save(UserVerificationDto verificationDto) {
 * 
 * User user = new User(verificationDto.getFirstName(),
 * registrationDto.getLastName(),registrationDto.getEmail(),passwordEncoder.
 * encode(registrationDto.getPassword()), Arrays.asList(new
 * Role("ROLE_USER")),registrationDto.getMobileNum(),registrationDto.
 * getCarRegNum(),registrationDto.getWallet()); return
 * userRepository.save(user);
 * 
 * }
 * 
 * 
 * 
 * @Override public void generateOTP(User user) throws
 * UnsupportedEncodingException, MessagingException { // TODO Auto-generated
 * method stub String OTP= RandomString.make(9); String eOTP=
 * pEncoder.encode(OTP); user.setOneTimePassword(eOTP);
 * user.setOtpRequestedTime(new Date()); userRepository.save(user);
 * sendOTPEmail(user,eOTP);
 * 
 * 
 * 
 * 
 * }
 * 
 * @Override public void sendOTPEmail(User user, String OTP) throws
 * UnsupportedEncodingException, MessagingException{ // TODO Auto-generated
 * method stub MimeMessage message=mailsender.createMimeMessage();
 * MimeMessageHelper helper= new MimeMessageHelper(message);
 * helper.setFrom("customersupport@parkplaza.com","Park Plaza Customer Support"
 * ); String subject="OTP expires in 5 minutes.Hurry!"; String content="<p> Hi "
 * + user.getFirstName()+"!</p>"+"Your OTP is </p>"+OTP+
 * "<br>"+"<p>This OTP will expire in 5 minutes </p>";
 * helper.setSubject(subject); helper.setText(content, true);
 * mailsender.send(message); }
 * 
 * @Override public void clearOTP(User user) { // TODO Auto-generated method
 * stub user.setOneTimePassword(null); user.setOtpRequestedTime(null);
 * userRepository.save(user);
 * 
 * }
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { User user =userRepository.findByEmail(username);
 * if(user==null) { throw new
 * UsernameNotFoundException("Invalid username or password."); }
 * 
 * return new
 * org.springframework.security.core.userdetails.User(user.getEmail(),
 * user.getPassword(),mapRolesToAuthorities(user.getRole())); } private
 * Collection<? extends GrantedAuthority >
 * mapRolesToAuthorities(Collection<Role> roles){ return
 * roles.stream().map(role-> new
 * SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
 * 
 * } }
 * 
 /* 
 * 
 * 
 * package net.codej.service;
 * 
 * import java.io.UnsupportedEncodingException; import java.util.Arrays; import
 * java.util.Collection; import java.util.Date; import
 * java.util.stream.Collectors;
 * 
 * import javax.mail.MessagingException; import javax.mail.internet.MimeMessage;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.mail.javamail.JavaMailSenderImpl; import
 * org.springframework.mail.javamail.MimeMessageHelper; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.stereotype.Service;
 * 
 * import net.bytebuddy.utility.RandomString; import net.codej.model.Role;
 * import net.codej.model.User; import net.codej.repository.UserRepository;
 * import net.codej.web.dto.UserRegistrationDto;
 * 
 * 
 * @Service public class UserServiceImpl implements UserServices{
 * 
 * private UserRepository userRepository;
 * 
 * @Autowired private BCryptPasswordEncoder passwordEncoder;
 * 
 * 
 * public UserServiceImpl(UserRepository userRepository) { super();
 * this.userRepository = userRepository; }
 * 
 * @Override public User save(UserRegistrationDto registrationDto) {
 * 
 * User user = new User(registrationDto.getFirstName(),
 * registrationDto.getLastName(),registrationDto.getEmail(),passwordEncoder.
 * encode(registrationDto.getPassword()), Arrays.asList(new
 * Role("ROLE_USER")),registrationDto.getMobileNum(),registrationDto.
 * getCarRegNum(),registrationDto.getWallet()); return
 * userRepository.save(user);
 * 
 * }
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException {
 * 
 * User user = userRepository.findByEmail(username); if(user == null) { throw
 * new UsernameNotFoundException("Invalid username or password."); } return new
 * org.springframework.security.core.userdetails.User(user.getEmail(),
 * user.getPassword(), mapRolesToAuthorities(user.getRoles())); }
 * 
 * 
 * public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { User user = userRepository.findByEmail(username);
 * if (user == null) { throw new
 * UsernameNotFoundException("Invalid username or password."); }
 * 
 * return new
 * org.springframework.security.core.userdetails.User(user.getEmail(),
 * user.getPassword(), mapRolesToAuthorities(user.getRole())); }
 * 
 * public UserServiceImpl() { super(); // TODO Auto-generated constructor stub }
 * 
 * private Collection<? extends GrantedAuthority>
 * mapRolesToAuthorities(Collection<Role> roles){ return roles.stream().map(role
 * -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
 * }
 * 
 * 
 * 
 * 
 * @Override public void generateOTP(net.codej.model.User user) { // TODO
 * Auto-generated method stub
 * 
 * }
 * 
 * @Override public void sendOTPEmail(net.codej.model.User user, String OTP) {
 * // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void clearOTP(net.codej.model.User user) { // TODO
 * Auto-generated method stub
 * 
 * }
 * 
 * 
 * }
 */