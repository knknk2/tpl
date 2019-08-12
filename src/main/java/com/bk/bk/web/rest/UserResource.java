package com.bk.bk.web.rest;

import com.bk.bk.domain.User;
import com.bk.bk.security.AuthoritiesConstants;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserResource {

  @GetMapping("/user")
  @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ANONYMOUS + "\")")
  public ResponseEntity<User> getUser() {
    User user = new User();
    user.setName("name");
    return ResponseEntity.ok().body(user);
  }
}
