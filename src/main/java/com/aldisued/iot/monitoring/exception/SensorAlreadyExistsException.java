package com.aldisued.iot.monitoring.exception;

import java.util.UUID;

public class SensorAlreadyExistsException extends RuntimeException {

    public SensorAlreadyExistsException(String name) {
        super("Sensor already exists with name: " + name);
    }
}
