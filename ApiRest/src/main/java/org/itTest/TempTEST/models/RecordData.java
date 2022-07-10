package org.itTest.TempTEST.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(name = "record_data")
public class RecordData {

    @Id
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "date")
    private LocalDate date;
}
