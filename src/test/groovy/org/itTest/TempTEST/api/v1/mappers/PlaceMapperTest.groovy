package org.itTest.TempTEST.api.v1.mappers

import org.itTest.TempTEST.api.v1.dto.request.PlaceRequest
import org.itTest.TempTEST.api.v1.dto.response.PlaceResponse
import org.itTest.TempTEST.models.Place
import org.itTest.TempTEST.models.Sensor
import org.mapstruct.factory.Mappers
import spock.lang.Specification


class PlaceMapperTest extends Specification {

    PlaceMapper placeMapper = Mappers.getMapper(PlaceMapper.class);

    def "Should map requestPlace into place object" () {
        given: "place request object with name populated"
        PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.name = "Loc 1";
        when: "place request mapped into place object"
        Place place = placeMapper.placeRequestToPlace(placeRequest);
        then: "Checking fields mapped"
        place != null;
        place.name == "Loc 1";
    }

    def "Should map Place into PlaceResponse object"() {
        given: "Sensor objects"
        Sensor sensor1 = new Sensor();
        sensor1.name = "temp 1";
        sensor1.uuid = "mspd-232";
        Sensor sensor2 = new Sensor();
        sensor2.name = "temp 2";
        sensor2.uuid = "dsm32-32";
        and: "place object populated"
        Place place = new Place();
        place.uuid = "ds32-ds219-mspf80";
        place.name = "Loc 1";
        place.sensors = List.of(sensor1, sensor2);
        sensor1.place = place;
        sensor2.place = place;
        when: "Place is mapped"
        PlaceResponse response = placeMapper.placeToPlaceResponse(place);
        then: "Checking response"
        response != null
        response.uuid == "ds32-ds219-mspf80";
        response.name == "Loc 1";
        response.sensors.size() > 0;
        response.sensors[0].name == "temp 1";
        response.sensors[0].uuid == "mspd-232";
        response.sensors[1].name == "temp 2";
        response.sensors[1].uuid == "dsm32-32";
    }

}