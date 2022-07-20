package org.itTest.TempTEST.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Table(name = "record_data")
@Entity
public class RecordData {

    @EmbeddedId
    private RecordDataCompositeKey recordDataKey;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "date")
    private LocalDate date;


}
