package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest;
import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.AverageTemperaturePerSensorByDateContainerResponse;
import org.itTest.TempTEST.api.v1.dto.response.PlaceItem;
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse;

import java.time.LocalDate;
import java.util.List;

public interface PlaceService {
    PlaceResponse registerPlace(PlaceRequest placeRequest);

    PlaceResponse findPlaceByUuid(String uuid);

    List<PlaceItem> findAllPlaces();
    PlaceResponse updatePlace(String uuid, PlaceRequest placeRequest);

    void deletePlaceByUuid(String uuid);

    AverageTemperaturePerSensorByDateContainerResponse getAverageTemperaturePerSensorByDateAndUuid(String uuid, LocalDate date);

    PlaceResponse addSensor(String uuid, SensorRequest sensor);
}
