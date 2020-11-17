package com.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.task.model.modelEnums.StopType;
import com.task.model.modelEnums.TapType;

import java.time.LocalDateTime;

/*
 *  Creation of the Tap model to be used to store the input from the CSV file.
 */

@AllArgsConstructor
@Data
@Builder
public class Tap {

    private String Id;
    private LocalDateTime tapTime;
    private TapType tapType;
    private StopType stop;
    private String companyId;
    private String busId;
    private String pan;
}
