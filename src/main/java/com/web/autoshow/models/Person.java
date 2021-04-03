package com.web.autoshow.models;

import com.web.autoshow.common.Sex;

import javax.persistence.*;

@Entity
public class Person {

  @Id @GeneratedValue
  private long id;

  @Column(length = 45)
  private String name;

  @Column(length = 45)
  private String surname;

  @Column(unique = true)
  private String phoneNumber;

  @Column(unique = true)
  private String email;

  private Sex sex;

  public Person() {

  }

  public Person(String name, String surname, String phoneNumber, String email, Sex sex) {
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.sex = sex;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }
}
