package org.itTest.TempTEST.api.v1.services

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse
import org.itTest.TempTEST.api.v1.service.SensorService
import org.itTest.TempTEST.api.v1.service.SensorServiceImpl
import org.itTest.TempTEST.exceptions.PlaceNotRegistered
import org.itTest.TempTEST.exceptions.SensorIsNotRegistered
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
        sensorService = new SensorServiceImpl(sensorRepository, placeRepository, urlUtils)
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
        response.name == "dds023"
        response.uuid == "sensorUUID"
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
        SensorResponse response = sensorService.registerSensor(sensorRequest)
        then:
        thrown PlaceNotRegistered
        1 * placeRepository.findById(_) >> Optional.ofNullable(null);
    }

    def "Should delete a sensor for place" () {
        given: "uuid of existent sensor"
        String uuid = "uuid"
        when: "sensor is deleted"
        sensorService.deleteSensorById(uuid)
        then:
        1 * sensorRepository.deleteById(_)
    }

    def "Should update a sensor name"() {
        given: "Sensor request dto"
        SensorRequest request = new SensorRequest()
        request.name = "nameUpdated"
        request.placeUuid = "uuid"
        and: "Place saved"
        Place place = new Place()
        place.uuid = "uuid"
        and: "Sensor saved in db"
        Sensor savedSensor = new Sensor()
        savedSensor.uuid = "uuid"
        savedSensor.name = "name"
        and: "Sensor updated"
        Sensor updatedSensor = new Sensor()
        updatedSensor.uuid = "uuid"
        updatedSensor.name = "nameUpdated"
        when:
        SensorResponse response = sensorService.updateSensorByUuid("uuid", request)
        then: "asserting response"
        response != null
        response.uuid == "uuid"
        response.name == "nameUpdated"
        and:
        1 * sensorRepository.findById(_) >> Optional.of(savedSensor)
        1 * placeRepository.findById(_)  >> Optional.of(place)
        1 * sensorRepository.save(_) >> updatedSensor
    }

    def "Should throw an exception when sensor is not registered" () {
        given: "Invalid sensor uuid"
        String uuid = "uuid"
        and: "sensor request"
        SensorRequest request = new SensorRequest()
        request.name = "name"
        when:
        SensorResponse response = sensorService.updateSensorByUuid(uuid, request)
        then:
        thrown SensorIsNotRegistered
        1 * sensorRepository.findById(_) >> Optional.ofNullable(null)
    }

    def "Should throw an exception when place is not found for update sensor" () {
        given: "Invalid place uuid"
        String placeuuid = "invalid"
        String uuid = "valid"
        and: "sensor request"
        SensorRequest request = new SensorRequest()
        request.placeUuid = placeuuid
        and: "sensor in db"
        Sensor sensor = new Sensor()
        sensor.name = "sensor"
        sensor.uuid = "valid"
        when:
        SensorResponse response = sensorService.updateSensorByUuid(uuid, request)
        then:
        thrown PlaceNotRegistered
        1 * sensorRepository.findById(_) >> Optional.ofNullable(sensor)
        1 * placeRepository.findById(_) >> Optional.ofNullable(null)
    }

    def "Should find a sensor by UUID" () {
        given: "uuid of existent sensor"
        String uuid = "uuid"
        and: "Sensor in db"
        Sensor sensor = new Sensor()
        sensor.uuid = uuid
        sensor.name = "name"
        and: "Sensor place"
        Place place  = new Place()
        place.uuid = "uuid"
        sensor.place = place
        and: "Links returned"
        Map<String, String> _links = new HashMap<>()
        _links.put("_self", "self")
        _links.put("_collection", "self")
        when:
        SensorResponse response = sensorService.findSensorByUUID(uuid)
        then:
        response. name == "name"
        response.uuid == "uuid"
        response._links == _links
        and:
        1 * sensorRepository.findById(_) >> Optional.of(sensor)
        1 * urlUtils.getLinksForSensorResponse(sensor) >> _links
    }
}