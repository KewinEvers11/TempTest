package org.itTest.TempTEST.exceptions;

import static org.itTest.TempTEST.exceptions.ExceptionMessages.CODE_4050;

public class PlaceNotRegistered extends RuntimeException{

    public PlaceNotRegistered (String uuid) {
        super(String.format(CODE_4050, uuid));
    }

}
