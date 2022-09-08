package org.it.Test.Temp.TEST.ApiRestTemperatureProducer.v1.controllers;

import org.it.Test.Temp.TEST.ApiRestTemperatureProducer.v1.requestModels.TemperatureRecordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.it.Test.Temp.TEST.ApiRestTemperatureProducer.services.TemperatureRecordService;

import javax.validation.Valid;

@RestController
@RequestMapping("/temp_records")
public class TemperatureRecordsController {

    private TemperatureRecordService temperatureRecordService;

    public TemperatureRecordsController (TemperatureRecordService temperatureRecordService){
        this.temperatureRecordService = temperatureRecordService;
    }

    @PostMapping({"/", ""})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void publishTemperatureRecord(@Valid @RequestBody TemperatureRecordRequest request){
        temperatureRecordService.createTemperatureRecord(request);
    }

}
