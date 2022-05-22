package org.itTest.TempTEST.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "date_record")
@Data
public class DateRecord {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    private LocalDateTime date;
}
