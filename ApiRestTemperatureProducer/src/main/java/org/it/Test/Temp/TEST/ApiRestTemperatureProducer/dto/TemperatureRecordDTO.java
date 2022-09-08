package org.it.Test.Temp.TEST.ApiRestTemperatureProducer.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class TemperatureRecordDTO {
    private final Double temperature;
    private LocalDate date;
    private LocalDateTime timestamp;
    private final String sensorUuid;
    public TemperatureRecordDTO(Double temperature, String sensorUuid){
        this.temperature = temperature;
        this.sensorUuid = sensorUuid;
    }

    public Double getTemperature(){
        return this.temperature;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public LocalDateTime getTimeStamp(){
        return this.timestamp;
    }

    public String getSensorUuid() { return this.sensorUuid;}

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
