package org.itTest.TempTEST.api.v1.mappers;

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse;
import org.itTest.TempTEST.models.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SensorMapper {

    SensorMapper INSTANCE = Mappers.getMapper(SensorMapper.class);

    SensorResponse sensorToSensorResponse(Sensor sensor);

    Sensor sensorRequestToSensor(SensorRequest request);

}
