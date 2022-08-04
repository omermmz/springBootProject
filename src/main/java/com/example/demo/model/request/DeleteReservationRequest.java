package com.example.demo.model.request;

import java.time.LocalDateTime;

public class DeleteReservationRequest {
    private Long id;

    public DeleteReservationRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
