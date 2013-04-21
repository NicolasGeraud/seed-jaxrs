package fr.client.project.security;

import org.springframework.security.core.GrantedAuthority;

public class RoleUser implements GrantedAuthority {
  public String getAuthority() {
    return "ROLE_USER";
  }
}
