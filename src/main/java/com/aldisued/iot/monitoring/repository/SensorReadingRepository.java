package com.aldisued.iot.monitoring.repository;

import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SensorReadingRepository extends JpaRepository<SensorReading, String> {

    @Query("SELECT AVG(sr.value) FROM SensorReading sr " +
            "JOIN sr.sensor s " +
            "WHERE sr.timestamp BETWEEN :from AND :to " +
            "AND s.type = :sensorType")
    Optional<Double> findAverageValueBySensorType(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("sensorType") SensorType sensorType);
}
