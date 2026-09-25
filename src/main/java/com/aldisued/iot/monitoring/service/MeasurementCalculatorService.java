package com.aldisued.iot.monitoring.service;


import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

    validateMovingAverageInputs(data, windowSize);

    double sum = data.stream().limit(windowSize).mapToDouble(Double::doubleValue).sum();

    List<Double> movingAverages = new ArrayList<>();
    movingAverages.add(sum / windowSize);

    for (int i = windowSize; i < data.size(); i++) {
      sum += data.get(i) - data.get(i - windowSize);
      movingAverages.add(sum / windowSize);
    }

    return movingAverages;
  }

  private void validateMovingAverageInputs(List<Double> data, int windowSize) {
    if (windowSize <= 0 ) {
      throw new IllegalArgumentException("windowSize must be positive");
    }
    if (CollectionUtils.isEmpty(data)) {
      throw new IllegalArgumentException("data cannot be empty");
    }
    if (data.size() < windowSize) {
      throw new IllegalArgumentException("windowSize is greater than the number of values");
    }
    if (data.stream().anyMatch(Objects::isNull))  {
      throw new IllegalArgumentException("none of the values can be null");
    }
  }
}
