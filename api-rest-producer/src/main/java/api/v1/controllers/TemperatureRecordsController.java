package api.v1.controllers;

import api.v1.requestModels.TemperatureRecordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("temp_records")
public class TemperatureRecordsController {

    @PostMapping({"/", ""})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void publishTemperatureRecord(@Valid @RequestBody TemperatureRecordRequest request){

    }

}
