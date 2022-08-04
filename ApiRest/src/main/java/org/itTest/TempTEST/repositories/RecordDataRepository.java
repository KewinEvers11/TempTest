package org.itTest.TempTEST.repositories;

import org.itTest.TempTEST.models.RecordData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RecordDataRepository  extends JpaRepository<RecordData, LocalDateTime> {
}
