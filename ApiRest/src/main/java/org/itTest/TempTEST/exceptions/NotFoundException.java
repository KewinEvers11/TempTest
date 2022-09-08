package org.itTest.TempTEST.exceptions;

public class NotFoundException extends RuntimeException{
    private String uuid;
    private String collection;
    public NotFoundException(String collection, String uuid) {
        this.uuid = uuid;
        this.collection = collection;
    }

    public String getUuid(){return uuid;}

    public String getCollection() {return collection;}

}
