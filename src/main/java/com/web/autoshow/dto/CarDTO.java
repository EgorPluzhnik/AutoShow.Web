package com.web.autoshow.dto;

import com.sun.mail.iap.ByteArray;

import java.util.Date;

public class CarDTO {
    private final ByteArray image;
    private final String model;
    private final String description;
    private final Date releaseDate;

    public CarDTO(ByteArray image, String model, String description, Date releaseDate) {
        this.image = image;
        this.model = model;
        this.description = description;
        this.releaseDate = releaseDate;
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
