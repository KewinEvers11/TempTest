package org.itTest.TempTEST.api.v1.dto.response;

import lombok.Data;

@Data
public class AverageTemperaturePerSensorDTO {
    private String sensorUuid;
    private String sensorName;
    private Double averageTemperature;
}
