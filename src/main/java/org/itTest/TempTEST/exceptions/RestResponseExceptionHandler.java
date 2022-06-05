package org.itTest.TempTEST.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestResponseExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage notFoundExceptionHandler(NotFoundException notFoundException) {
        return new ExceptionMessage(notFoundException.getMessage(), SeverityError.LOW);
    }

    @ExceptionHandler(value = {PlaceNotRegistered.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage placeNotRegisteredPlaceException(PlaceNotRegistered placeNotRegistered) {
        return new ExceptionMessage(placeNotRegistered.getMessage(), SeverityError.LOW);
    }

}
