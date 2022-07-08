package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest;
import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.PlaceItem;
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse;
import org.itTest.TempTEST.api.v1.mappers.PlaceMapper;
import org.itTest.TempTEST.api.v1.mappers.SensorMapper;
import org.itTest.TempTEST.exceptions.NotFoundException;
import org.itTest.TempTEST.models.Place;
import org.itTest.TempTEST.models.Sensor;
import org.itTest.TempTEST.repositories.PlaceRepository;
import org.itTest.TempTEST.utils.UrlUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.itTest.TempTEST.exceptions.ExceptionMessages.CODE_4004;

@Service
public class PlaceServiceImpl implements PlaceService{

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper  = PlaceMapper.INSTANCE;
    private final SensorMapper sensorMapper = SensorMapper.INSTANCE;

    private final UrlUtils urlUtils;
    public PlaceServiceImpl(PlaceRepository placeRepository, UrlUtils urlUtils) {
        this.placeRepository = placeRepository;
        this.urlUtils = urlUtils;
    }

    @Override
    public PlaceResponse registerPlace(PlaceRequest placeRequest) {
        Place place = placeRepository.save(placeMapper.placeRequestToPlace(placeRequest));
        return placeMapper.placeToPlaceResponse(place);
    }

    @Override
    public List<PlaceItem> findAllPlaces(){
        return placeRepository.findAll()
                .stream()
                .map(place -> placeMapper.placeToPlaceItem(place))
                .peek(p -> p.set_links(urlUtils.getLinksForPlaceItem(p)))
                .collect(Collectors.toList());
    }

    @Override
    public PlaceResponse findPlaceByUuid(String uuid) throws NotFoundException {
        Place placeFound = placeRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(String.format(CODE_4004, "Place", uuid)));
        return placeMapper.placeToPlaceResponse(placeFound);
    }

    @Override
    @Transactional
    public PlaceResponse updatePlace(String uuid, PlaceRequest placeRequest) {
        Place placeFound= placeRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(String.format(CODE_4004, "Place", uuid)));
        placeFound.setName(placeRequest.getName());
        return placeMapper.placeToPlaceResponse(placeRepository.save(placeFound));
    }

    @Override
    public void deletePlaceByUuid(String uuid) {
        placeRepository.deleteById(uuid);
    }

    @Override
    @Transactional
    public PlaceResponse addSensor(String uuid, SensorRequest sensorRequest){
        Place place = placeRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(String.format(CODE_4004, "Place", uuid)));

        Sensor newSensor = sensorMapper.sensorRequestToSensor(sensorRequest);
        newSensor.setPlace(place);
        if(place.getSensors() != null) {
            place.getSensors().add(newSensor);
        }else{
            place.setSensors(List.of(newSensor));
        }
        PlaceResponse placeUpdated = placeMapper.placeToPlaceResponse( placeRepository.save(place));
        addLinksToSensors(placeUpdated);
        return placeUpdated;
    }


    private  void addLinksToSensors(PlaceResponse response) {
        response.getSensors()
                .stream()
                .forEach(sensorItem -> {
                    sensorItem.set_links(urlUtils.getLinksForSensorItem(sensorItem));
                });
    }

}
