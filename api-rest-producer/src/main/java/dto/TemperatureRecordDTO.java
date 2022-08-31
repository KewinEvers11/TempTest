package dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TemperatureRecordDTO {
    @NotNull
    @Min(-50)
    @Max(100)
    private Double Temperature;

}
