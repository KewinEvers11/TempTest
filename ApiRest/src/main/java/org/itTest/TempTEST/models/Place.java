package org.itTest.TempTEST.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.List;
@Entity
@Table(name = "place")
@Data
public class Place {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Sensor> sensors;
}
