package org.itTest.TempTEST.api.v1.services.validation

import org.itTest.TempTEST.api.v1.dto.request.RecordDataItemRequest
import org.itTest.TempTEST.api.v1.dto.request.RecordDataListRequest
import org.itTest.TempTEST.models.RecordData
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.ValidatorFactory
import javax.validation.Validator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class RecordDataListRequestValidationSpecs extends Specification{

    private Validator validator

    def setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    def "RecordDataListRequest field validation - success"() {
        given: "request right populated"
        RecordDataItemRequest item = new RecordDataItemRequest()
        item.temperature = 32.4D
        LocalDate date = LocalDate.of(2022, 7, 14)
        LocalTime time = LocalTime.of(12, 5)
        item.timestamp = LocalDateTime.of(date, time)

        RecordDataListRequest request = new RecordDataListRequest()
        request.uuidSensor = "sensor"
        request.date = LocalDate.of(2022,7,14)
        request.records = List.of(item)

        when: "validates the recordDataListRecord"
        Set<ConstraintViolation<RecordDataItemRequest>> violations = validator.validate(request)

        then: "No violations should be presented"
        violations.size() == 0
    }

    def "RecordDataListRequest field validations for null values"() {
        given: "RecordDataListRequest "
        RecordDataListRequest request = new RecordDataListRequest()
        request.records = null
        request.date = null
        request.uuidSensor = null

        when: "RecordDataListRequest is validated"

        Set<ConstraintViolation<RecordDataListRequest>> violations = validator.validate(request)

        then: "All violations size"
        violations.size() == 3
    }

    def "Check validation for records list with size 0"() {
        given: "RecordDataListRequest with empty records"
        RecordDataListRequest request = new RecordDataListRequest()
        request.uuidSensor = "sensor"
        request.date = LocalDate.of(2022, 07, 14)
        request.records = new ArrayList<>()

        when: "RecordDataListRequest object is validated"
        Set<ConstraintViolation<RecordDataListRequest>> violations = validator.validate(request)

        then: "one violations must be triggered"
        violations.size() == 1
    }

}
