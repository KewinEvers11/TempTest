package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.RecordDataListRequest;
import org.itTest.TempTEST.api.v1.mappers.RecordDataMapper;
import org.itTest.TempTEST.exceptions.SensorIsNotRegistered;
import org.itTest.TempTEST.models.RecordData;
import org.itTest.TempTEST.models.Sensor;
import org.itTest.TempTEST.repositories.RecordDataRepository;
import org.itTest.TempTEST.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.itTest.TempTEST.exceptions.ExceptionMessages.CODE_4051;

@Service
public class RecordDataServiceImpl implements RecordDataService{


    private final SensorRepository sensorRepository;
    private final RecordDataRepository recordDataRepository;

    private final RecordDataMapper recordDataMapper = RecordDataMapper.INSTANCE;


    public RecordDataServiceImpl(SensorRepository sensorRepository,
                                 RecordDataRepository recordDataRepository) {
        this.sensorRepository = sensorRepository;
        this.recordDataRepository = recordDataRepository;
    }


    @Override
    public void registerRecords(RecordDataListRequest request) {

        String sensorUuid = request.getUuidSensor();

        // check sensor existence
        Sensor sensor = sensorRepository.findById(sensorUuid)
                .orElseThrow(() -> new SensorIsNotRegistered(CODE_4051, sensorUuid));

        List<RecordData> records = request.getRecords()
                .stream()
                .map(r -> recordDataMapper.recordDataItemRequestToRecordData(r))
                .peek(rd -> rd.setDate(request.getDate()))
                .peek(rd -> rd.setSensor(sensor))
                .collect(Collectors.toList());


        recordDataRepository.saveAll(records);
    }
}
