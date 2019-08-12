package com.bk.bk.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.bk.bk.security.jwt.SamAuthenticationToken;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the {@link SecurityUtils} utility class.
 */
public class SecurityUtilsUnitTest {

  @Test
  public void testGetCurrentUserLogin() {
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(new SamAuthenticationToken("admin", "admin", 1L));
    SecurityContextHolder.setContext(securityContext);
    Optional<String> login = SecurityUtils.getCurrentUserLogin();
    assertThat(login).contains("admin");
  }

  @Test
  public void testCurrentUserUUID() {
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(new SamAuthenticationToken("admin", "admin", 1L));
    SecurityContextHolder.setContext(securityContext);
    Optional<Long> uuid = SecurityUtils.getCurrentUserUUID();
    assertThat(uuid).contains(1L);
  }

  @Test
  public void testgetCurrentUserJWT() {
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(new SamAuthenticationToken("admin", "token", 1L));
    SecurityContextHolder.setContext(securityContext);
    Optional<String> jwt = SecurityUtils.getCurrentUserJWT();
    assertThat(jwt).contains("token");
  }

  @Test
  public void testIsAuthenticated() {
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(new SamAuthenticationToken("admin", "admin", 1L));
    SecurityContextHolder.setContext(securityContext);
    boolean isAuthenticated = SecurityUtils.isAuthenticated();
    assertThat(isAuthenticated).isTrue();
  }

  @Test
  public void testAnonymousIsNotAuthenticated() {
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ANONYMOUS));
    securityContext.setAuthentication(new SamAuthenticationToken("anonymous", "anonymous", authorities, 1L));
    SecurityContextHolder.setContext(securityContext);
    boolean isAuthenticated = SecurityUtils.isAuthenticated();
    assertThat(isAuthenticated).isFalse();
  }

  @Test
  public void testIsCurrentUserInRole() {
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.USER));
    securityContext.setAuthentication(new SamAuthenticationToken("user", "user", authorities, 1L));
    SecurityContextHolder.setContext(securityContext);

    assertThat(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)).isTrue();
    assertThat(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)).isFalse();
  }

}
