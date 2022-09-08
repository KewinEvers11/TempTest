package org.it.Test.Temp.TEST.ApiRestTemperatureProducer.services;

import org.it.Test.Temp.TEST.ApiRestTemperatureProducer.v1.requestModels.TemperatureRecordRequest;

public interface TemperatureRecordService {
    void createTemperatureRecord(TemperatureRecordRequest request);
}
