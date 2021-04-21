package com.web.autoshow.dto;

import java.util.Date;

public class ShowDTO {
    private final long carId;

    private final long userId;

    private final Date date;

    public long getCarId() {
        return carId;
    }

    public long getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public ShowDTO(long carId, long userId, Date date) {
        this.carId = carId;
        this.userId = userId;
        this.date = date;
    }
}
