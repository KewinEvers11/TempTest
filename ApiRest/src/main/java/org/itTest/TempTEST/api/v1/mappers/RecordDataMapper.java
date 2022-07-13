package org.itTest.TempTEST.api.v1.mappers;

import org.itTest.TempTEST.api.v1.dto.request.RecordDataItemRequest;
import org.itTest.TempTEST.api.v1.dto.request.RecordDataRequest;
import org.itTest.TempTEST.api.v1.dto.response.RecordDataResponse;
import org.itTest.TempTEST.models.RecordData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecordDataMapper {

    RecordDataMapper INSTANCE = Mappers.getMapper(RecordDataMapper.class);

    RecordDataResponse recordDataToRecordDataResponse(RecordData recordData);

    @Mapping(source = "temp", target = "temperature")
    RecordData recordDataRequestToRecordData(RecordDataRequest request);

    RecordData recordDataItemRequestToRecordData(RecordDataItemRequest recordItem);

}
