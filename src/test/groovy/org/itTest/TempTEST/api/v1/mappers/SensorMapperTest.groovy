package org.itTest.TempTEST.api.v1.mappers

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse
import org.itTest.TempTEST.models.Place
import org.itTest.TempTEST.models.Sensor
import org.mapstruct.factory.Mappers
import spock.lang.Specification


class SensorMapperTest extends Specification {

    SensorMapper sensorMapper;

    def setup() {
        sensorMapper = Mappers.getMapper(SensorMapper.class);
    }
    
    def "test sensor to sensorResponse mapper" (){
        given:  "Sensor object populated with all data"
        Sensor sensor = new Sensor()
        sensor.uuid = "uuid"
        sensor.name = "sensor"
        when: "Sensor object is mapped into sensor response"
        SensorResponse response = sensorMapper.sensorToSensorResponse(sensor);
        then: "Asserting response"
        response.name == "sensor"
        response.uuid == "uuid"
    }

    def "test sensor request to sensor" () {
        given: "Sensor request populated with all data"
        SensorRequest request = new SensorRequest()
        request.name = "name"
        when: "Sensor request is mapped into a sensor object"
        Sensor sensor = sensorMapper.sensorRequestToSensor(request)
        then: "Asserting sensor"
        sensor.name == "name"
    }

}