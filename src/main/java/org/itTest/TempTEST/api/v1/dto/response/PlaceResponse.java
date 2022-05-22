package org.itTest.TempTEST.api.v1.dto.response;

import lombok.Data;
import org.itTest.TempTEST.models.Sensor;

import java.util.List;

@Data
public class PlaceResponse {
    private String uuid;
    private String name;
    private List<SensorItem> sensors;
}
