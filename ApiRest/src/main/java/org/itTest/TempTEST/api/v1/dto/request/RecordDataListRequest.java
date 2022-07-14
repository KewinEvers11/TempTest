package org.itTest.TempTEST.api.v1.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class RecordDataListRequest {

    @NotNull
    private LocalDate date;

    @Size(min = 1)
    @NotNull
    private List<RecordDataItemRequest> records;

    @NotNull
    private String uuidSensor;
}
