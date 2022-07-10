package org.itTest.TempTEST.api.v1.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RecordDataResponse {
    private LocalDateTime timestamp;
    private LocalDate date;
    private Double temperature;
}
