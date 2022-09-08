package org.it.Test.Temp.TEST.ApiRestTemperatureProducer.v1.requestModels;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TemperatureRecordRequest {
    @NotNull
    @Min(-50)
    @Max(100)
    private Double temperature;

    @NotNull
    private String sensorUuid;

    public TemperatureRecordRequest() {

    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setSensorUuid(String sensorUuid) {
        this.sensorUuid = sensorUuid;
    }

    public String getSensorUuid() {
        return this.sensorUuid;
    }
}
