package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse;
import org.itTest.TempTEST.api.v1.mappers.SensorMapper;
import org.itTest.TempTEST.exceptions.NotFoundException;
import org.itTest.TempTEST.exceptions.PlaceNotRegistered;
import org.itTest.TempTEST.exceptions.SensorIsNotRegistered;
import org.itTest.TempTEST.models.Place;
import org.itTest.TempTEST.models.Sensor;
import org.itTest.TempTEST.repositories.PlaceRepository;
import org.itTest.TempTEST.repositories.SensorRepository;
import org.itTest.TempTEST.utils.UrlUtils;

import static org.itTest.TempTEST.exceptions.ExceptionMessages.CODE_4004;
import static org.itTest.TempTEST.exceptions.ExceptionMessages.CODE_4050;
import static org.itTest.TempTEST.exceptions.ExceptionMessages.CODE_4051;

public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final PlaceRepository placeRepository;
    private final SensorMapper sensorMapper = SensorMapper.INSTANCE;
    private final UrlUtils urlUtils;
    public SensorServiceImpl(SensorRepository sensorRepository, PlaceRepository placeRepository, UrlUtils urlUtils) {
        this.sensorRepository = sensorRepository;
        this.placeRepository = placeRepository;
        this.urlUtils = urlUtils;
    }

    @Override
    public SensorResponse registerSensor(SensorRequest sensorRequest) {
        Place place = placeRepository.findById(sensorRequest.getPlaceUuid())
                .orElseThrow(() -> new PlaceNotRegistered(sensorRequest.getPlaceUuid()));
        Sensor newSensor = sensorMapper.sensorRequestToSensor(sensorRequest);
        newSensor.setPlace(place);
        SensorResponse response = sensorMapper.sensorToSensorResponse(sensorRepository.save(newSensor));
        response.set_links(urlUtils.getLinksForSensorResponse(newSensor));
        return response;
    }

    @Override
    public void deleteSensorById(String uuid) {
        sensorRepository.deleteById(uuid);
    }

    @Override
    public SensorResponse updateSensorByUuid(String uuid, SensorRequest sensorRequest) {
        // Check sensor exist
        Sensor sensor = sensorRepository.findById(uuid)
                .orElseThrow(() -> new SensorIsNotRegistered(String.format(CODE_4050, uuid),uuid));
        // Check place exist
        Place place = placeRepository.findById(sensorRequest.getPlaceUuid())
                .orElseThrow(() -> new PlaceNotRegistered(sensorRequest.getPlaceUuid()));
        Sensor sensorToUpdate = sensorMapper.sensorRequestToSensor(sensorRequest);
        sensorToUpdate.setUuid(sensor.getUuid());
        sensorToUpdate.setPlace(place);
        SensorResponse response = sensorMapper.sensorToSensorResponse(sensorRepository.save(sensorToUpdate));
        response.set_links(urlUtils.getLinksForSensorResponse(sensorToUpdate));
        return response;
    }

    @Override
    public SensorResponse findSensorByUUID(String uuid) {
        Sensor sensor = sensorRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(String.format(CODE_4004, "Sensor", uuid)));
        SensorResponse response = sensorMapper.sensorToSensorResponse(sensor);
        response.set_links(urlUtils.getLinksForSensorResponse(sensor));
        return response;
    }
}
