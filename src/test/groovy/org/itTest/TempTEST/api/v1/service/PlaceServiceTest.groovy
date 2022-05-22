package org.itTest.TempTEST.api.v1.service

import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest
import org.itTest.TempTEST.api.v1.dto.request.SensorRequest
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse
import org.itTest.TempTEST.api.v1.dto.response.SensorResponse
import org.itTest.TempTEST.exceptions.NotFoundException
import org.itTest.TempTEST.models.Place
import org.itTest.TempTEST.models.Sensor
import org.itTest.TempTEST.repositories.PlaceRepository
import org.itTest.TempTEST.utils.UrlUtils
import spock.lang.Specification


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
        List<PlaceResponse> responses = placeService.findAllPlaces();
        then: "checking responses"
        responses.get(0).uuid == "p1"
        responses.get(1).uuid == "p2"
        and: "Specifying mock behaviour"
        1 * placeRepository.findAll() >> places;
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
}