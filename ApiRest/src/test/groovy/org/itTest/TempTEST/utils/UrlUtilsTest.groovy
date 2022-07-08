package org.itTest.TempTEST.utils

import org.itTest.TempTEST.api.v1.dto.response.PlaceItem
import org.itTest.TempTEST.api.v1.dto.response.SensorItem
import org.itTest.TempTEST.models.Place
import org.itTest.TempTEST.models.Sensor
import spock.lang.Specification

class UrlUtilsTest extends Specification {

    UrlUtils urlUtils
    static String DOMAIN_NAME = "domain_name"
    def setup() {
        urlUtils = new UrlUtils(DOMAIN_NAME)
    }

    def "test getLinksForSensorItem"() {
        given:
        SensorItem sensorItem = new SensorItem()
        sensorItem.uuid = "sensorUUID"
        when:
        // TODO implement stimulus
        Map<String, String> links =  urlUtils.getLinksForSensorItem(sensorItem)
        then:
        // TODO implement assertions
        links.get("_self") == "domain_name/sensors/sensorUUID"
    }

    def "test getLinksForSensorResponse"() {
        given:
        Sensor sensor = new Sensor()
        sensor.uuid = "sensorUUID"
        sensor.place = new Place()
        sensor.place.uuid = "placeUUID"
        when:
        // TODO implement stimulus
        Map<String, String> links = urlUtils.getLinksForSensorResponse(sensor)
        then:
        // TODO implement assertions
        links.get("_self")  == "domain_name/sensors/sensorUUID"
        links.get("_collection") == "domain_name/places/placeUUID/sensors"
    }

    def "test getLinksForPlaceItem"() {
        given:
        PlaceItem placeItem = new PlaceItem()
        placeItem.uuid = "placeUUID"
        when:
        // TODO implement stimulus
        Map<String, String> links = urlUtils.getLinksForPlaceItem(placeItem)
        then:
        // TODO implement assertions
        links.get("_self") == "domain_name/places/placeUUID"
    }
}
