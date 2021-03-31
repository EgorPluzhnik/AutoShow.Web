package com.web.autoshow.models;

import com.sun.mail.iap.ByteArray;
import java.util.Date;

public class CarDTO {
    private final int id;
    private final ByteArray image;
    private final String model;
    private final String description;
    private final Date releaseDate;

    public CarDTO(int id, ByteArray image, String model, String description, Date releaseDate) {
        this.id = id;
        this.image = image;
        this.model = model;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public ByteArray getImage() {
        return image;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
