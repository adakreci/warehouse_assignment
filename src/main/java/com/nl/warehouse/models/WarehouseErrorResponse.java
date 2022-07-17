package com.nl.warehouse.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarehouseErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
}
