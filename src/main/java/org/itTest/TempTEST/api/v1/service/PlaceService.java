package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest;
import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.PlaceItem;
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse;

import java.util.List;

public interface PlaceService {
    PlaceResponse registerPlace(PlaceRequest placeRequest);

    PlaceResponse findPlaceByUuid(String uuid);

    List<PlaceItem> findAllPlaces();
    PlaceResponse updatePlace(String uuid, PlaceRequest placeRequest);

    void deletePlaceByUuid(String uuid);

    PlaceResponse addSensor(String uuid, SensorRequest sensor);
}
