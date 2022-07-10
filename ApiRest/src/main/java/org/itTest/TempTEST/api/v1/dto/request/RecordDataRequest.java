package org.itTest.TempTEST.api.v1.dto.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
public class RecordDataRequest {

    @NotNull
    @Digits(integer = 4, fraction = 2)
    private Double temp;

}
