package org.itTest.TempTEST.api.v1.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class AverageTempPerSensorRequest {
    @NotNull
    @Size(max=255)
    private String uuid;
    @NotNull
    private LocalDate date;
}
