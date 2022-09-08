package org.itTest.TempTEST.exceptions;

import static org.itTest.TempTEST.exceptions.ExceptionMessages.CODE_4050;

public class PlaceNotRegistered extends RuntimeException{

    private String uuid;
    public PlaceNotRegistered (String uuid) {
        this.uuid = uuid;
    }

    public String getUuid(){
        return uuid;
    }

}
