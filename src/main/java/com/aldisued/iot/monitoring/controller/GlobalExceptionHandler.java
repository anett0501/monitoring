package com.aldisued.iot.monitoring.controller;

import com.aldisued.iot.monitoring.exception.AlertNotFoundException;
import com.aldisued.iot.monitoring.exception.SensorAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SensorAlreadyExistsException.class)
    public ResponseEntity<?> handleSensorAlreadyExistsException(SensorAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(AlertNotFoundException.class)
    public ResponseEntity<?> handleAlertNotFoundException(AlertNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
