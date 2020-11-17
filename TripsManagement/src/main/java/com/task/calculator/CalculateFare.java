package com.task.calculator;

import com.task.model.modelEnums.StopType;
import java.time.LocalDateTime;

/*
 * Methods of this interface are implemented in TransactionCalculator class.
 */
public interface CalculateFare {
    double calculateTripCost(StopType initialPoint, StopType destination);

    Long calculateTimeTaken(LocalDateTime started, LocalDateTime finished);
}
