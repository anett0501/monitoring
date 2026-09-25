package com.aldisued.iot.monitoring.exception;

import java.util.UUID;

public class AlertNotFoundException extends RuntimeException {

    public AlertNotFoundException(UUID sensorId) {
        super("Alert not found with sensorId: " + sensorId);
    }
}
