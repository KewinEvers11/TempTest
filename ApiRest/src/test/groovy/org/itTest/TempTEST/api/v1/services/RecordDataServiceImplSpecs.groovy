package org.itTest.TempTEST.api.v1.services

import org.itTest.TempTEST.api.v1.dto.request.RecordDataListRequest
import org.itTest.TempTEST.api.v1.service.RecordDataService
import org.itTest.TempTEST.api.v1.service.RecordDataServiceImpl
import org.itTest.TempTEST.exceptions.SensorIsNotRegistered
import org.itTest.TempTEST.repositories.RecordDataRepository
import org.itTest.TempTEST.repositories.SensorRepository
import spock.lang.Specification

class RecordDataServiceImplSpecs extends Specification {

    private RecordDataRepository recordDataRepository
    private SensorRepository sensorRepository
    private RecordDataService recordDataService

    def setup() {
        recordDataRepository = Mock(RecordDataRepository.class)
        sensorRepository = Mock(SensorRepository.class)

        recordDataService = new RecordDataServiceImpl(sensorRepository, recordDataRepository)
    }

    def "Should throw an exception " () {
        given: "RecordDataRequestList with non existent sensor uuid"
        RecordDataListRequest request = new RecordDataListRequest()
        request.uuidSensor = "no"

        when: "Register a list for the sensor"
        recordDataService.registerRecords(request)
        then: "mock responses"
        sensorRepository.findById(_) >> Optional.ofNullable(null)
        and: "Exception call should be sent"
        thrown SensorIsNotRegistered
    }

}
