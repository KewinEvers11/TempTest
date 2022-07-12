package org.itTest.TempTEST.repositories;

import org.itTest.TempTEST.models.RecordData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RecordDataRepositoryImpl implements RecordDataRepository{
    @PersistenceContext
    private final EntityManager em;


    public RecordDataRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    /**
     *  Saves the batch
     * @param recordDataList: RecordData List populated with all data
     */
    @Override
    public void createBatchRecordData(List<RecordData> recordDataList) {

    }
}
