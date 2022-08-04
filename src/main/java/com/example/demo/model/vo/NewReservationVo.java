package com.example.demo.model.vo;

import java.time.LocalDateTime;

public class NewReservationVo {

    private String placeName;
    private String userName;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private LocalDateTime date;

    public NewReservationVo() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
