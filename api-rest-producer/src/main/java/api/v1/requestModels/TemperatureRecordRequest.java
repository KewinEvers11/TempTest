package api.v1.requestModels;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TemperatureRecordRequest {
    @NotNull
    @Min(-50)
    @Max(100)
    private Double Temperature;

}
