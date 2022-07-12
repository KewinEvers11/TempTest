package org.itTest.TempTEST.api.v1.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RecordDataListRequest {
    private LocalDate date;
    private List<RecordDataItemRequest> records;
}
