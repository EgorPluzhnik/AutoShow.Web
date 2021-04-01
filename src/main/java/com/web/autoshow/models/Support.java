package com.web.autoshow.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Support {

  @Id @GeneratedValue
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person personId;

  @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
  private Date date;

  @Column(length = 300)
  private String question;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Person getPersonId() {
    return personId;
  }

  public void setPersonId(Person personId) {
    this.personId = personId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }
}
