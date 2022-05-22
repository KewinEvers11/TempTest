package org.itTest.TempTEST.api.v1.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class PlaceItem {
    private String uuid;
    private String name;
    private Map<String, String> _links;
}
