package fr.client.project.security;

import org.springframework.security.core.GrantedAuthority;

public class RoleAdmin implements GrantedAuthority {
  public String getAuthority() {
    return "ROLE_ADMIN";
  }
}
