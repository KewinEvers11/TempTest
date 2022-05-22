package org.itTest.TempTEST.api.v1.mappers;

import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest;
import org.itTest.TempTEST.api.v1.dto.response.PlaceItem;
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse;
import org.itTest.TempTEST.api.v1.dto.response.SensorItem;
import org.itTest.TempTEST.models.Place;
import org.itTest.TempTEST.models.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    Place placeRequestToPlace(PlaceRequest placeRequest);

    PlaceResponse placeToPlaceResponse(Place place);

    SensorItem sensorToSensorItem(Sensor sensor);

    PlaceItem placeToPlaceItem(Place place);
    default List<SensorItem> sensorListToSensorItemList(List<Sensor> sensorList){
        if(sensorList == null)
            return new ArrayList<>();
        return sensorList.stream()
                .map(sensor -> sensorToSensorItem(sensor))
                .collect(Collectors.toList());
    }
}
