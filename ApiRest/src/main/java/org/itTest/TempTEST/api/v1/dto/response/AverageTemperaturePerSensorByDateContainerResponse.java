package org.itTest.TempTEST.api.v1.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AverageTemperaturePerSensorByDateContainerResponse {

    private String placeName;
    private String placeUuid;
    private List<AverageTemperaturePerSensorDTO> avgTemperatures;

}
