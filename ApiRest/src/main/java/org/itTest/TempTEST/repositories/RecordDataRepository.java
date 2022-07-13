package org.itTest.TempTEST.repositories;

import org.itTest.TempTEST.models.RecordData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RecordDataRepository  extends JpaRepository<RecordData, LocalDateTime> {
}
