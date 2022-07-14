package org.itTest.TempTEST.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sensor")
@Data
public class Sensor {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String uuid;

    @Column(name = "name", nullable = false )
    private String name;

    @ManyToOne
    private Place place;

    @OneToMany(mappedBy = "sensor")
    private List<RecordData> records;

}
