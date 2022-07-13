package org.itTest.TempTEST.api.v1.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class RecordDataListRequest {
    private LocalDate date;
    private List<RecordDataItemRequest> records;

    @NotNull
    private String uuidSensor;
}
