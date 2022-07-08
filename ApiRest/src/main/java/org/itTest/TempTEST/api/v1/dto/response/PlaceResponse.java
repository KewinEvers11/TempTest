package org.itTest.TempTEST.api.v1.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PlaceResponse {
    private String uuid;
    private String name;
    private List<SensorItem> sensors;
}
