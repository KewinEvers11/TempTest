package org.itTest.TempTEST.utils;


import org.itTest.TempTEST.api.v1.dto.response.PlaceItem;
import org.itTest.TempTEST.api.v1.dto.response.SensorItem;
import org.itTest.TempTEST.models.Sensor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UrlUtils {

    @Value("${app.domain_name}")
    private String baseUrl;

    public UrlUtils(){}

    public UrlUtils(String baseUrl) {
        this.baseUrl =baseUrl;
    }

    private static String SELF = "_self";
    private static String COLLECTION = "_collection";
    public Map<String, String> getLinksForSensorItem(SensorItem sensor) {
        Map<String, String> _links = new HashMap<>();

        _links.put(SELF, String.format(baseUrl + "/sensors/%s", sensor.getUuid()));

        return _links;
    }

    public Map<String, String> getLinksForSensorResponse(Sensor sensor) {
        Map<String, String> _links = new HashMap<>();

        _links.put(SELF, String.format(baseUrl + "/sensors/%s", sensor.getUuid()));
        _links.put(COLLECTION, String.format(baseUrl + "/places/%s/sensors", sensor.getPlace().getUuid()));

        return _links;
    }

    public Map<String, String> getLinksForPlaceItem(PlaceItem placeItem) {
        Map<String, String> _links = new HashMap<>();

        _links.put(SELF, String.format(baseUrl + "/places/%s", placeItem.getUuid()));

        return _links;
    }

}
