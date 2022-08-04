package org.itTest.TempTEST.api.v1.services

import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest
import org.itTest.TempTEST.api.v1.dto.request.SensorRequest
import org.itTest.TempTEST.api.v1.dto.response.AverageTemperaturePerSensorByDateContainerResponse
import org.itTest.TempTEST.api.v1.dto.response.PlaceItem
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse
import org.itTest.TempTEST.api.v1.service.PlaceService
import org.itTest.TempTEST.api.v1.service.PlaceServiceImpl
import org.itTest.TempTEST.exceptions.NotFoundException
import org.itTest.TempTEST.models.Place
import org.itTest.TempTEST.models.Sensor
import org.itTest.TempTEST.pojo.AvgTemperaturePerSensorPojo
import org.itTest.TempTEST.repositories.PlaceRepository
import org.itTest.TempTEST.utils.UrlUtils
import spock.lang.Specification
import java.time.LocalDate

class PlaceServiceTest extends Specification {

    static PlaceRepository placeRepository;
    static UrlUtils urlUtils;
    static PlaceService placeService;
    def setup(){

        placeRepository = Mock();
        urlUtils = Mock();
        placeService = new PlaceServiceImpl(placeRepository,urlUtils);
    }

    def "Should save a place"(){
        given: "Place Request object populated with name"
        PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.name = "name";
        and: "Place object returned by repository"
        Place place = new Place();
        place.uuid = "uuid";
        place.name = "name";

        when: "Place is create in the service"
        PlaceResponse response = placeService.registerPlace(placeRequest);
        then: "Checking place registration"
        response.name == "name";
        response.uuid == "uuid";
        response.sensors.size() == 0;
        and: "Dependencies calls"
        1 * placeRepository.save(_) >> place;
    }

    def "Should returned all places saved" () {
        given: "List of places saved returned by repository"
        Place p1 = new Place()
        p1.uuid = "p1"
        Place p2 = new Place()
        p2.uuid = "p2"
        List<Place> places = List.of(p1, p2);
        when: "find all places is called"
        List<PlaceItem> responses = placeService.findAllPlaces()
        then: "checking responses"
        responses.get(0).uuid == "p1"
        responses.get(1).uuid == "p2"
        and: "Specifying mock behaviour"
        1 * placeRepository.findAll() >> places
    }

    def "Should find a place by using a Id" () {
        given: "Uuid existent in db"
        String uuid = "uuid";
        and: "Place object in db"
        Place place = new Place();
        place.uuid = "uuid";
        when: "Find place is called"
        PlaceResponse response = placeService.findPlaceByUuid(uuid);
        then: "Asserting field"
        response.uuid == "uuid";
        and: "Specifying calls"
        1 * placeRepository.findById(_) >> Optional.of(place);
    }

    def "Should throw a not found exception when uuid not found" () {
        given: "not existent uuid existent in db"
        String uuid = "uuid";
        and: "Place object in db"
        Place place = null;
        when: "Find place is called"
        PlaceResponse response = placeService.findPlaceByUuid(uuid);
        then: "Asserting field"
        thrown NotFoundException
        and: "Specifying calls"
        1 * placeRepository.findById(_) >> Optional.ofNullable(place);
    }

    def "Should update a place by using its id" () {
        given: "Object returned by db with valid uuid"
        String uuid = "uuid";
        Place place = new Place();
        place.uuid = uuid;
        place.name = "name";
        and: "Place object updated in the db"
        Place placeUpdated = new Place();
        placeUpdated.uuid = uuid;
        placeUpdated.name = "update name";
        and: "Place request to update place"
        PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.name = "update name";
        when: "Place is updated in service"
        PlaceResponse response = placeService.updatePlace(uuid, placeRequest);
        then: "Asserting name field"
        response.name == "update name";
        and: "Dependency calls"
        1 * placeRepository.findById(_) >> Optional.of(place);
        1 * placeRepository.save(_) >> placeUpdated;
    }

    def "Should delete place by using its uuid" (){
        given: "Existent uuid for a place in DB"
        String uuid = "uuid";
        when: "Delete place by uuid is called"
        placeService.deletePlaceByUuid(uuid);
        then:"Asserting deleting from data base called"
        1 * placeRepository.deleteById(_);
    }


    def "Should add a new sensor" () {
        given: "Necessary objects to add a new sensor"
        def uuid = "uuid";
        SensorRequest sensorRequest = new SensorRequest();
        sensorRequest.name = "sensor";

        and: "Existent place in data base"
        Place place = new Place();
        place.uuid = uuid;
        place.sensors = new ArrayList<>();

        and: "Sensor object created in db"
        Sensor sensor = new Sensor();
        sensor.name  = "sensor";
        sensor.uuid = "sensorUuid";

        and: "Place updated in database"
        Place placeUpdated = new Place();
        placeUpdated.uuid = uuid;
        placeUpdated.sensors = List.of(sensor);

        and: "Map return by field"
        Map<String, String> _links = Map.of("_self", "url");

        when: "A new sensor is registered"
        PlaceResponse response = placeService.addSensor(uuid, sensorRequest);

        then: "response field checked"
        response.sensors.size() == 1
        response.sensors.get(0).uuid == "sensorUuid";
        response.sensors.get(0)._links.get("_self") == "url"

        and:
        1 * placeRepository.findById(_) >> Optional.of(place);
        1 * urlUtils.getLinksForSensorItem(_) >> _links;
        1 * placeRepository.save(_) >> placeUpdated;
    }

    def "Should request get a success AverageTemperaturePerSensorByDateContainerResponse"() {

        given: "an existent place"
        Place place = new Place()
        place.uuid = "uuid"
        place.name = "basement"
        place.sensors = new ArrayList()

        and: "list of average temperatures data"
        def avg1 = new AvgTempImpl("uuid1", "sensor1", 36.7d)
        def avg2 = new AvgTempImpl("uuid2", "sensor2", 35.5d)
        List<AvgTemperaturePerSensorPojo> dataList = List.of(avg1, avg2)

        when: "get AverageTemperaturePerSensor for the place"
        AverageTemperaturePerSensorByDateContainerResponse result = placeService.getAverageTemperaturePerSensorByDateAndUuid("uuid", LocalDate.of(2022, 8, 3))

        then: "Checking results"
        result.placeName == "basement"
        result.placeUuid == "uuid"
        result.avgTemperatures[0].sensorUuid == "uuid1"
        result.avgTemperatures[0].sensorName == "sensor1"
        result.avgTemperatures[0].averageTemperature == 36.7d
        result.avgTemperatures[1].sensorUuid == "uuid2"
        result.avgTemperatures[1].sensorName == "sensor2"
        result.avgTemperatures[1].averageTemperature == 35.5d

        and: "Mocking repositories"
        1 * placeRepository.findById(_) >> Optional.of(place)
        1 * placeRepository.getAvgTemperaturePerSensor(_, _) >> dataList
    }

    def "Test getAverageTemperaturePerSensorByDateAndUuid "() {
        given: "an uuid of a non existent place object"
        String uuid = "noexist"
        Place place = null

        when: "get AverageTemperaturePerSensor for not found place"
        placeService.getAverageTemperaturePerSensorByDateAndUuid(uuid, LocalDate.of(2022, 7, 3))

        then: "Place not found exception thrown"
        thrown NotFoundException
        and: "Mocking placeRepository response"
        1 * placeRepository.findById(_) >> Optional.ofNullable(place)
    }

    def "Test getAverageTemperaturePerSensorByDateAndUuid but not record was found" () {
        given: "an existent place"
        Place place = new Place()
        place.name = "basement"
        place.uuid = "uuid"
        place.sensors = new ArrayList<>()

        when: "get AverageTemperaturePerSensor for place with no records"
        AverageTemperaturePerSensorByDateContainerResponse result = placeService.getAverageTemperaturePerSensorByDateAndUuid("uuid", LocalDate.of(2022, 7,3))

        then: "resultant query with no records"
        result.placeName == "basement"
        result.placeUuid == "uuid"
        result.avgTemperatures == []

        and: "mocking repository responses"
        1 * placeRepository.findById(_) >> Optional.of(place)
        1 * placeRepository.getAvgTemperaturePerSensor(_,_) >> new ArrayList<>()
    }
}
/**
 * Implementation in other to not mock a list of AverageTemperatureBySensorPojo
 */
class AvgTempImpl implements AvgTemperaturePerSensorPojo {

    private String sensorUuid
    private String sensorName
    private Double averageTemperature

    public AvgTempImpl(String sensorUuid, String sensorName, Double averageTemperature) {
        this.sensorUuid = sensorUuid
        this.sensorName = sensorName
        this.averageTemperature = averageTemperature
    }

    @Override
    String getSensorUuid() {
        return sensorUuid
    }

    @Override
    String getSensorName() {
        return sensorName
    }

    @Override
    Double getAverageTemperature() {
        return averageTemperature
    }
}