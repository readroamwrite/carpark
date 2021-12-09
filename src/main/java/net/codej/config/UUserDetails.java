/*
 * package net.codej.config;
 * 
 * import java.util.ArrayList; import java.util.Collection;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import antlr.collections.List; import net.codej.model.Role; import
 * net.codej.model.User;
 * 
 * public class UUserDetails implements UserDetails { private User user; public
 * UUserDetails(User user) { this.user=user;
 * 
 * }
 * 
 * @Override public String getPassword() { if(user.isOTPReqd()) { return
 * user.getOneTimePassword();
 * 
 * } return user.getPassword(); }
 * 
 * @Override public boolean isAccountNonExpired() { return true; }
 * 
 * @Override public String getUsername() { // TODO Auto-generated method stub
 * return user.getEmail(); }
 * 
 * @Override public boolean isAccountNonLocked() { // TODO Auto-generated method
 * stub return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() { // TODO Auto-generated
 * method stub return false; }
 * 
 * @Override public boolean isEnabled() { // TODO Auto-generated method stub
 * return true; }
 * 
 * @Override public Collection< ? extends GrantedAuthority> getAuthorities() {
 * Collection<Role> roles= user.getRole();
 * Collection<SimpleGrantedAuthority>authorities= new ArrayList<>(); for(Role
 * rol: roles) { authorities.add(new SimpleGrantedAuthority(rol.getName())); }
 * // TODO Auto-generated method stub return authorities; }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * }
 */