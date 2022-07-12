package org.itTest.TempTEST.api.v1.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordDataItemRequest {

    private LocalDateTime timestamp;
    private Double temperature;

}
