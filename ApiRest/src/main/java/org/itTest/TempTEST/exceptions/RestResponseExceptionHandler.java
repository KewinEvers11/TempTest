package org.itTest.TempTEST.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.itTest.TempTEST.exceptions.ExceptionMessages.*;

@ControllerAdvice
public class RestResponseExceptionHandler {

    private MessageSource messageSource;

    public RestResponseExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage notFoundExceptionHandler(NotFoundException notFoundException) {
        String errorMessage =  messageSource.getMessage(CODE_4004, new Object[] {notFoundException.getUuid(), notFoundException.getCollection()},  LocaleContextHolder.getLocale());
        return new ExceptionMessage(errorMessage, SeverityError.LOW);
    }

    @ExceptionHandler(value = {PlaceNotRegistered.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage placeNotRegisteredPlaceException(PlaceNotRegistered placeNotRegistered) {
        String errorMessage = messageSource.getMessage(CODE_4050, new Object[] {placeNotRegistered.getUuid()}, LocaleContextHolder.getLocale());
        return new ExceptionMessage(errorMessage, SeverityError.LOW);
    }

    @ExceptionHandler(value = {SensorIsNotRegistered.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage sensorIsNotRegisteredHandlerException(SensorIsNotRegistered sensorIsNotRegistered) {
        String errorMessage = messageSource.getMessage(CODE_4051, new Object[] {sensorIsNotRegistered.getUuid()}, LocaleContextHolder.getLocale());
        return new ExceptionMessage(errorMessage, SeverityError.LOW);
    }


}
