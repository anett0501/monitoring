package com.aldisued.iot.monitoring.service;


import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class MeasurementCalculatorService {

  public List<Double> filterByAverageDeviation(List<Double> values, Double deviation) {
    if (deviation == null || deviation < 0.0 || deviation > 1.0) {
      throw new IllegalArgumentException("deviation must be in range [0.0, 0.1]");
    }
    if (CollectionUtils.isEmpty(values)) {
      return List.of();
    }

    double average = values.stream().filter(Objects::nonNull)
            .mapToDouble(Double::doubleValue).average()
            .orElseThrow(() -> new IllegalArgumentException("Average cannot be calculated"));
    double lowerBound = average - average * deviation;
    double upperBound = average + average * deviation;

    return values.stream().filter(v -> v >= lowerBound && v <= upperBound).toList();
  }

  public List<Double> getMovingAverage(List<Double> data, int windowSize) {
    // TODO: Task 10
    return List.of();
  }

}
