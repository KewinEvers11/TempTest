package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse;

import java.util.List;

public interface SensorService {
    SensorResponse registerSensor(SensorRequest sensorRequest);

    void deleteSensorById(String uuid);

    SensorResponse updateSensorByUuid(String uuid, SensorRequest sensorRequest);

    SensorResponse findSensorByUUID(String uuid);
}
