package org.itTest.TempTEST.api.v1.service

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse
import org.itTest.TempTEST.exceptions.PlaceNotRegistered
import org.itTest.TempTEST.models.Place
import org.itTest.TempTEST.models.Sensor
import org.itTest.TempTEST.repositories.PlaceRepository
import org.itTest.TempTEST.repositories.SensorRepository
import org.itTest.TempTEST.utils.UrlUtils
import spock.lang.Specification


class SensorServiceTest extends Specification {

    SensorService sensorService

    SensorRepository sensorRepository

    UrlUtils urlUtils
    PlaceRepository placeRepository


    def setup(){
        sensorRepository = Mock()
        placeRepository = Mock()
        urlUtils = Mock()
        sensorService = new SensorServiceImpl(sensorRepository, placeRepository)
    }

    def "Should create an sensor with an existent Place" () {
        given: "Sensor request object populated"
        SensorRequest sensorRequest = new SensorRequest()
        sensorRequest.name = "dds023"
        sensorRequest.placeUuid = "placeUUID"
        and: "Place object in db"
        Place place = new Place()
        place.uuid = "placeUUID"
        and: "Sensor created in db"
        Sensor sensor = new Sensor()
        sensor.uuid = "sensorUUID"
        sensor.name = "dds023"
        and: "links"
        Map<String, String> links = new HashMap<>()
        when: "Sensor is registered in service"
        SensorResponse response = sensorService.registerSensor(sensorRequest)
        then:
        response.name = "dds023"
        response.uuid = "sensorUUID"
        and:
        1 * sensorRepository.save(_) >> sensor
        1 * placeRepository.findById(_) >> Optional.of(place)
        1 * urlUtils.getLinksForSensorResponse(_) >> links
    }

    def "Should throw non existent place exception" () {
        given: "Sensor request with invalid place uuid"
        SensorRequest sensorRequest = new SensorRequest()
        sensorRequest.name = "name"
        sensorRequest.placeUuid = "non"
        when:
        SensorResponse response = sensorService.registerSensor(sensorService)
        then:
        thrown PlaceNotRegistered
        1 * placeRepository.findById(_) >> Optional.ofNullable(null);
    }

}