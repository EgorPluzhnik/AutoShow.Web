package com.web.autoshow.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "model" }))
public class Car {
  @Id @GeneratedValue
  private long id;

  @Column(length = 50)
  private String model;

  @Column(length = 10000)
  private String description;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date releaseDate;

  private String image;

  public Car(String model, String description, Date releaseDate, String image) {
    this.model = model;
    this.description = description;
    this.releaseDate = releaseDate;
    this.image = image;
  }

  public Car() {

  }

  public long getCarId() {
    return id;
  }

  public void setCarId(long carId) {
    this.id = carId;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public String getImage() {
    return image;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }
}
