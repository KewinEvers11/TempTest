package org.itTest.TempTEST.api.v1.mappers

import org.itTest.TempTEST.api.v1.dto.request.RecordDataItemRequest
import org.itTest.TempTEST.api.v1.dto.request.RecordDataRequest
import org.itTest.TempTEST.api.v1.dto.response.RecordDataResponse
import org.itTest.TempTEST.models.RecordData
import org.itTest.TempTEST.models.RecordDataCompositeKey
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class RecordDataMapperTest extends Specification {

    static RecordDataMapper mapper;
    static LocalDateTime TIMESTAMP
    static LocalTime TIME
    static LocalDate DATE

    def setup() {
        mapper = RecordDataMapper.INSTANCE
        DATE =  LocalDate.of(2022, 7, 9)
        TIME = LocalTime.of(15, 30)
        TIMESTAMP = LocalDateTime.of(DATE, TIME)
    }

    def "Test map RecordData to RecordDataResponse"() {
        given: "RecordData populated"
        RecordData recordData = new RecordData()
        recordData.temperature = 32.4D
        recordData.date = DATE
        recordData.recordDataKey = new RecordDataCompositeKey()
        recordData.recordDataKey.timestamp = TIMESTAMP

        when: "RecordDate is mapped"
        RecordDataResponse response = mapper.recordDataToRecordDataResponse(recordData)

        then: "Check response"
        response.timestamp == TIMESTAMP
        response.date == DATE
        response.temperature == 32.4D
    }


    def "Test map RecordDataRequest to RecordData"() {
        given: "RecordDataRequest populated"
        RecordDataRequest request = new RecordDataRequest()
        request.temp = 34.5D

        when: "RecordDataRequest is mapped to RecordData"
        RecordData recordData = mapper.recordDataRequestToRecordData(request)

        then: "Asserting record data"
        recordData.temperature == 34.5D
    }

    def "Test map RecordDataRequestItem to RecordData"() {
        given: "RecordDataRequest item populated"

        RecordDataItemRequest request = new RecordDataItemRequest()
        request.temperature = 43.4D
        request.timestamp = TIMESTAMP

        when: "RecordDataRequestItem is mapped to RecordData"
        RecordData recordData = mapper.recordDataItemRequestToRecordData(request)

        then: "asserting field for record data"
        recordData.recordDataKey.timestamp == TIMESTAMP
        recordData.temperature == 43.4D
    }


}
