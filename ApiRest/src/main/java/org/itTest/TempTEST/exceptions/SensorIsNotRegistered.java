package org.itTest.TempTEST.exceptions;

public class SensorIsNotRegistered extends RuntimeException{

    private final String uuid;
    public SensorIsNotRegistered( String uuid){
        this.uuid = uuid;
    }

    public String getUuid(){
        return uuid;
    }
}
