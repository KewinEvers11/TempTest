package org.itTest.TempTEST.repositories;

import org.itTest.TempTEST.models.RecordData;

import java.util.List;

public interface RecordDataRepository {

    void createBatchRecordData(List<RecordData> recordDataList);

}
