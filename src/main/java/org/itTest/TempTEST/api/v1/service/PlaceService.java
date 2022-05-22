package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest;
import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.PlaceItem;
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse;

import java.util.List;

public interface PlaceService {
    public PlaceResponse registerPlace(PlaceRequest placeRequest);

    public PlaceResponse findPlaceByUuid(String uuid);

    public List<PlaceItem> findAllPlaces();
    public PlaceResponse updatePlace(String uuid, PlaceRequest placeRequest);

    public void deletePlaceByUuid(String uuid);

    public PlaceResponse addSensor(String uuid, SensorRequest sensor);
}
