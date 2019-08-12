package com.bk.bk.security;

import com.bk.bk.config.ApplicationConstants;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(ApplicationConstants.SYSTEM_ACCOUNT));
    // return Optional.of(ApplicationConstants.SYSTEM_ACCOUNT);
  }
}
