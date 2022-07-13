package org.itTest.TempTEST.api.v1.service;

import org.itTest.TempTEST.api.v1.dto.request.RecordDataListRequest;
import org.itTest.TempTEST.models.RecordData;

import java.util.List;

public interface RecordDataService {
    void registerRecords(RecordDataListRequest request);
}
