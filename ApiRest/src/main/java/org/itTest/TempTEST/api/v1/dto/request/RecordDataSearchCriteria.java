package org.itTest.TempTEST.api.v1.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RecordDataSearchCriteria {

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

}
