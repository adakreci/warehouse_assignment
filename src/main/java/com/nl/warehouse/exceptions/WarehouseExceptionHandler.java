package com.nl.warehouse.exceptions;

import com.nl.warehouse.models.WarehouseErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class WarehouseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<WarehouseErrorResponse> productNotFoundException(Exception ex) {
        return getWarehouseErrorResponseResponseEntity(ex);
    }

    @ExceptionHandler(NoProductException.class)
    public ResponseEntity<WarehouseErrorResponse> productNotInStockException(Exception ex) {
        return getWarehouseErrorResponseResponseEntity(ex);
    }

    private ResponseEntity<WarehouseErrorResponse> getWarehouseErrorResponseResponseEntity(Exception ex) {
        WarehouseErrorResponse response = new WarehouseErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setError(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
