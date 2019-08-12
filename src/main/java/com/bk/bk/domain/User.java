package com.bk.bk.domain;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String login;

  private String password;

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param login the login to set
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public int hashCode() {
    return Optional.ofNullable(id).map(id -> 31 * 17 * (int) (id ^ (id >>> 32))).orElse(31);
  }
}
