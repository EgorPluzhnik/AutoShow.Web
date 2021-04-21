package com.web.autoshow.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "car_id", "person_id" }))
public class Show {

  @Id @GeneratedValue
  private long id;

  @ManyToOne
  @JoinColumn(name = "car_id")
  private Car carId;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person personId;

  @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
  private Date date;

  public Show(Car carId, Person personId, Date date) {
    this.carId = carId;
    this.personId = personId;
    this.date = date;
  }

  public Show() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Car getCarId() {
    return carId;
  }

  public void setCarId(Car carId) {
    this.carId = carId;
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
}
