package org.itTest.TempTEST.repositories;

import org.itTest.TempTEST.models.Place;
import org.itTest.TempTEST.pojo.AvgTemperatureBySensorPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {

    @Query(value = "SELECT s.uuid AS sensorUuid, s.name AS sensorName, AVG(r.temperature) AS averageTemperature\n" +
            "\tFROM public.sensor s\n" +
            "\tLEFT JOIN (SELECT * \n" +
            "\t\t\t\tFROM public.record_data \n" +
            "\t\t\t\tWHERE date = :date \n" +
            "\t\t\t\tORDER BY timestamp_record_data) r\n" +
            "\tON s.uuid = r.sensor_uuid\n" +
            "\tWHERE s.place_uuid = :place_uuid\n" +
            "\tGROUP BY s.uuid, s.name", nativeQuery = true)
    List<AvgTemperatureBySensorPojo> getAvgTemperaturePerSensor(@Param("date") LocalDate date, @Param("place_uuid") String placeUuid);

}
