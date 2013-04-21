package fr.client.project.security;

import fr.client.project.entity.User;
import fr.client.project.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomAuthenticationManager implements AuthenticationManager {

  protected static Logger logger = Logger.getLogger(CustomAuthenticationManager.class);

  @Autowired
  UsersRepository usersRepository;

  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    // Init a database user object
    User user = null;

    try {
      // Retrieve user details from database
      user = usersRepository.findByEmail(auth.getName());
    } catch (Exception e) {
      logger.error("User does not exists!");
      throw new BadCredentialsException("User does not exists!");
    }

    // Compare passwords
    // Use a proper way with hashing etc...
    if (auth.getCredentials() == null || !auth.getCredentials().equals(user.getPassword())) {
      logger.error("Wrong password!");
      throw new BadCredentialsException("Wrong password!");
    }

    return new UsernamePasswordAuthenticationToken(
        auth.getName(),
        auth.getCredentials(),
        getAuthorities(user.isAdmin()));
  }

  /**
   * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
   * Basically, this interprets the access value whether it's for a regular user or admin.
   *
   * @param access an integer value representing the access of the user
   * @return collection of granted authorities
   */
  public static Collection<GrantedAuthority> getAuthorities(boolean access) {
    // Create a list of grants for this user
    List<GrantedAuthority> authList = new ArrayList<>();

    // All users are granted with ROLE_USER access
    // Therefore this user gets a ROLE_USER by default
    authList.add(new RoleUser());

    // Check if this user has admin access
    if (access) {
      // User has admin access
      authList.add(new RoleAdmin());
    }

    // Return list of granted authorities
    return authList;
  }

}