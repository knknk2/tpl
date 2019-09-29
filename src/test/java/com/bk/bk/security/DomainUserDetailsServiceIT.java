package com.bk.bk.security;

import com.bk.bk.BkApplication;
import com.bk.bk.domain.User;
import com.bk.bk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integrations tests for {@link DomainUserDetailsService}.
 */
@SpringBootTest(classes = BkApplication.class)
@Transactional
public class DomainUserDetailsServiceIT {

  private static final String USER_ONE_LOGIN = "test-user-one";
  private static final String USER_TWO_LOGIN = "test-user-two";
  private static final String USER_THREE_LOGIN = "test-user-three";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserDetailsService domainUserDetailsService;

  private User userOne;
  private User userTwo;
  private User userThree;

  @BeforeEach
  public void init() {
    userOne = new User();
    userOne.setLogin(USER_ONE_LOGIN);
      userOne.setName("user1");
      userOne.setPassword("pass1");
    userRepository.save(userOne);

    userTwo = new User();
    userTwo.setLogin(USER_TWO_LOGIN);
      userTwo.setName("user2");
      userTwo.setPassword("pass2");
    userRepository.save(userTwo);

    userThree = new User();
    userThree.setLogin(USER_THREE_LOGIN);
      userThree.setName("user3");
      userThree.setPassword("pass3");
    userRepository.save(userThree);
  }

  @Test
  @Transactional
  public void assertThatUserCanBeFoundByLogin() {
    UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_ONE_LOGIN);
    assertThat(userDetails).isNotNull();
    assertThat(userDetails.getUsername()).isEqualTo(USER_ONE_LOGIN);
  }

  @Test
  @Transactional
  public void assertThatUserCanBeFoundByLoginIgnoreCase() {
    UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_ONE_LOGIN.toUpperCase(Locale.ENGLISH));
    assertThat(userDetails).isNotNull();
    assertThat(userDetails.getUsername()).isEqualTo(USER_ONE_LOGIN);
  }
}
