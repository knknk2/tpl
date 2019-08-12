package com.bk.bk.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SamAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = 1L;

  public Long getUUID() {
    return uuid;
  }

  private final Long uuid;

  public SamAuthenticationToken(Object principal, Object credentials, Long uuid) {
    super(principal, credentials);
    this.uuid = uuid;
  }

  public SamAuthenticationToken(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities, Long uuid) {
    super(principal, credentials, authorities);
    this.uuid = uuid;
  }
}
