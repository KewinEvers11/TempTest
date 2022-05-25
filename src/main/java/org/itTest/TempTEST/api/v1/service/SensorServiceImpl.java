package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse;
import org.itTest.TempTEST.api.v1.mappers.SensorMapper;
import org.itTest.TempTEST.repositories.PlaceRepository;
import org.itTest.TempTEST.repositories.SensorRepository;
import org.itTest.TempTEST.utils.UrlUtils;

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
        return null;
    }

    @Override
    public void deleteSensorById(String uuid) {

    }

    @Override
    public SensorResponse updateSensorByUuid(String uuid, SensorRequest sensorRequest) {
        return null;
    }

    @Override
    public SensorResponse findSensorByUUID(String uuid) {
        return null;
    }
}
