package org.itTest.TempTEST.exceptions;

import org.itTest.TempTEST.api.v1.dto.request.SensorRequest;

public class SensorIsNotRegistered extends RuntimeException{

    private final String uuid;
    public SensorIsNotRegistered(String message, String uuid){
        super(message);
        this.uuid = uuid;
    }

    public String getUuid(){
        return uuid;
    }
}
