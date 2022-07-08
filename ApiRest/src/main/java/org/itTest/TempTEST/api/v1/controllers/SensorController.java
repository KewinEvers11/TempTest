package org.itTest.TempTEST.api.v1.controllers;

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse;
import org.itTest.TempTEST.api.v1.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/sensors")
public class SensorController {

    SensorService sensorService;

    public SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }

    @PostMapping(value = {"", "/"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public SensorResponse registerNewSensor(@RequestBody SensorRequest sensorRequest) {
        return sensorService.registerSensor(sensorRequest);
    }

    @GetMapping(value = {"/uuid", "/uuid/"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public SensorResponse findSensorByUUID(@PathVariable String uuid) {
        return sensorService.findSensorByUUID(uuid);
    }

    @DeleteMapping(value = {"/uuid", "/uuid/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensorByUUID(@PathVariable String uuid) {
        sensorService.deleteSensorById(uuid);
    }

    @PutMapping(value = {"/uuid", "/uuid/"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public SensorResponse updateSensorByUUID(@PathVariable String uuid, @RequestBody SensorRequest sensorRequest){
        return sensorService.updateSensorByUuid(uuid, sensorRequest);
    }
}
