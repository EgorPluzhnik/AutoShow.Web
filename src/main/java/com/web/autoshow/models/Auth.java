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

  public Auth(String login, String password, Person personId) {
    this.login = login;
    this.password = password;
    this.personId = personId;
  }

  public long getPid() {
    return pid;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public Person getPersonId() {
    return personId;
  }
}
