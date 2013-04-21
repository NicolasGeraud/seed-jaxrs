package fr.client.project.security;

import fr.client.project.entity.User;
import fr.client.project.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  protected static Logger logger = Logger.getLogger(CustomUserDetailsService.class);

  @Autowired
  UsersRepository usersRepository;

  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {

    User user = usersRepository.findByEmail(username);

    return new org.springframework.security.core.userdetails.User(username, null, CustomAuthenticationManager.getAuthorities(user.isAdmin()));
  }
}
