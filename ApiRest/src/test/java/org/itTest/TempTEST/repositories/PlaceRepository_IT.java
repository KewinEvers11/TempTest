package org.itTest.TempTEST.repositories;

import org.itTest.TempTEST.models.Place;
import org.itTest.TempTEST.models.RecordData;
import org.itTest.TempTEST.models.RecordDataCompositeKey;
import org.itTest.TempTEST.models.Sensor;
import org.itTest.TempTEST.pojo.AvgTemperatureBySensorPojo;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableJpaRepositories
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQL10Dialect",
        "spring.jpa.hibernate.ddl-auto= create",
        "spring.datasource.url=jdbc:postgresql://localhost:5432/tempTestQA",
        "spring.jpa.properties.hibernate.format_sql=true",
        "spring.jpa.show-sql= true",
        "spring.datasource.username= kewin",
        "spring.datasource.password= kewin"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlaceRepository_IT {

    @Autowired
    PlaceRepository placeRepository;
    private static final Logger log = LoggerFactory.getLogger(PlaceRepository_IT.class.getSimpleName());
    @PersistenceContext
    private EntityManager em;

    private Place place;

    private Map<String, Double> dict;

    @BeforeEach
    public void createPlaceWithSensor() {

        place = new Place();
        place.setName("lugar");

        Sensor s1, s2, s3;
        s1 = new Sensor();
        s2 = new Sensor();
        s3 = new Sensor();

        s1.setName("sensor_1");
        s1.setPlace(place);
        s2.setName("sensor_2");
        s2.setPlace(place);
        s3.setName("sensor_3");
        s3.setPlace(place);

        List<Sensor> sensors = List.of(s1, s2, s3);
        place.setSensors(sensors);

        em.persist(place);

        dict = new HashMap<>();
        for(int i = 0; i < place.getSensors().size(); i++) {
            generateRecordData(place.getSensors().get(i), 10);
        }

        em.persist(place);
        em.flush();
    }

    @Test
    public void testGetAverageTemperatureBySensorsForPlace() {

        // test averageTemperatureBySensor

        Place place1 = placeRepository.findById(place.getUuid())
                .get();

        assertNotNull(place1);
        log.info("Place found");

        List<AvgTemperatureBySensorPojo> avg = placeRepository.getAvgTemperaturePerSensor(LocalDate.now(), place.getUuid());

        log.info("results: " + avg);

        assertNotNull(avg);
        assertTrue(avg.size() > 1);
    }

    private void generateRecordData(Sensor sensor, int n) {
        RecordData rd ;
        List<RecordData> records = new ArrayList<>();
        RecordDataCompositeKey key;
        LocalDate date = LocalDate.now();

        for(int i = 0; i < n ; i++) {
            rd = new RecordData();
            key = new RecordDataCompositeKey();
            key.setSensor(sensor);
            key.setTimestamp(LocalDateTime.now().plusMinutes(i * 5));
            rd.setRecordDataKey(key);
            rd.setDate(date);
            rd.setTemperature(Math.random() * 10);


            records.add(rd);

            if(!dict.containsKey(sensor.getUuid())) {
                dict.put(sensor.getUuid(), rd.getTemperature());
            } else{
                dict.put(sensor.getUuid(), dict.get(sensor.getUuid()) + rd.getTemperature());
            }
        }

        sensor.setRecords(records);

        em.persist(sensor);

        dict.put(sensor.getUuid(), dict.get(sensor.getUuid()) / n);
    }

}
