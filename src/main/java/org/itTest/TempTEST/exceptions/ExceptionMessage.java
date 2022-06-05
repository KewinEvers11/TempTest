package org.itTest.TempTEST.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionMessage {
    private String message;
    private SeverityError severity;
}
