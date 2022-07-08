package org.itTest.TempTEST.api.v1.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SensorRequest {
    @NotNull
    private String name;
    private String placeUuid;
}
