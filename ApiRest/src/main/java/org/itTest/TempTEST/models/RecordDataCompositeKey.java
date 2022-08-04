package org.itTest.TempTEST.models;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class RecordDataCompositeKey implements Serializable {

    @Column(name = "timestamp_record_data")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sensor sensor;

    public RecordDataCompositeKey(){}

    public RecordDataCompositeKey(LocalDateTime timestamp, Sensor sensor) {
        this.sensor = sensor;
        this.timestamp = timestamp;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Sensor getSensor() { return sensor;}

    public LocalDateTime getTimestamp() {return timestamp;}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RecordDataCompositeKey rdCK = (RecordDataCompositeKey) o;

        return Objects.equals(rdCK.getSensor().getUuid() , sensor.getUuid()) &&
                Objects.equals(rdCK.getTimestamp(), timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, sensor);
    }

    @Override
    public String toString() {
        return String.format("[timestamp: %s, sensor: %s]", timestamp, sensor.getUuid());
    }
}
