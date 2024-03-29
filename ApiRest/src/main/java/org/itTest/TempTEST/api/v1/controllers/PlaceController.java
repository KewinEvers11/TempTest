package org.itTest.TempTEST.api.v1.controllers;

import org.itTest.TempTEST.api.v1.dto.request.AverageTempPerSensorRequest;
import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest;
import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.AverageTemperaturePerSensorByDateContainerResponse;
import org.itTest.TempTEST.api.v1.dto.response.PlaceItem;
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse;
import org.itTest.TempTEST.api.v1.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceResponse registerPlace(@Valid @RequestBody PlaceRequest placeRequest) {
        return placeService.registerPlace(placeRequest);
    }

    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<PlaceItem> getAllPlaces(){
        return placeService.findAllPlaces();
    }

    @GetMapping({"/{uuid}", "/{uuid}/"})
    @ResponseStatus(HttpStatus.OK)
    public PlaceResponse findPlaceById(@PathVariable String uuid) {
        return placeService.findPlaceByUuid(uuid);
    }

    @PostMapping({"/avgData"})
    @ResponseStatus(HttpStatus.OK)
    public AverageTemperaturePerSensorByDateContainerResponse getAverageTemperatePerSensorByPlaceUuidAndDate(@Valid @RequestBody AverageTempPerSensorRequest averageTempPerSensorRequest) {
        return placeService.getAverageTemperaturePerSensorByDateAndUuid(averageTempPerSensorRequest.getUuid(), averageTempPerSensorRequest.getDate());
    }

    @PutMapping({"/{uuid}", "/{uuid}/"})
    @ResponseStatus(HttpStatus.OK)
    public PlaceResponse updatePlaceByUuid(@PathVariable String uuid, @RequestBody PlaceRequest request) {
        return placeService.updatePlace(uuid, request);
    }

    @DeleteMapping({"/{uuid}", "/{uuid}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@PathVariable String uuid){
        placeService.deletePlaceByUuid(uuid);
    }

    @PostMapping({"/{uuid}/addSensor", "/{uuid}/addSensor/"})
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceResponse addNewSensorToPlace(@PathVariable String uuid, @RequestBody SensorRequest sensorRequest) {
        return placeService.addSensor(uuid, sensorRequest);
    }

}
