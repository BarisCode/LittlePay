package com.task.model;

import com.task.model.modelEnums.StatusType;
import com.task.model.modelEnums.StopType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Creation of the Trip model to be used to store the output from the CSV file.
 */

@AllArgsConstructor
@Data
@Builder
public class Trip {

    private LocalDateTime started;
    private LocalDateTime finished;
    // duration in seconds
    private Long duration;
    // cost of the trip
    private double chargeAmount;
    // one of the three stops decribed in StopType
    private StopType Fromstop;
    private StopType Tostop;
    private String companyId;
    private String busId;
    private String pan;
    // one of the status described in enum StatusType
    private StatusType status;

}
