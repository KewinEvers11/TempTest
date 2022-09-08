package org.it.Test.Temp.TEST.ApiRestTemperatureProducer.services;

import org.it.Test.Temp.TEST.ApiRestTemperatureProducer.v1.requestModels.TemperatureRecordRequest;
import org.springframework.stereotype.Service;

@Service
public class TemperatureRecordServiceImpl implements TemperatureRecordService {

    private PublisherService publisherService;

    public TemperatureRecordServiceImpl(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Override
    public void createTemperatureRecord(TemperatureRecordRequest request) {
        publisherService.publishTemperatureRecord(request, "TEMPERATURE");
    }
}
