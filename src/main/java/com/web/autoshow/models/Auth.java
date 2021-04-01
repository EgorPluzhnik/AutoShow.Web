package com.web.autoshow.models;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"login", "password"}))
public class Auth {

  @Id @GeneratedValue
  private long pid;

  @Column(length = 30)
  private String login;

  @Column(length = 45)
  private String password;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person personId;

  public Auth() {

  }

  public long getUid() {
    return pid;
  }

  public void setUid(long uid) {
    this.pid = uid;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
