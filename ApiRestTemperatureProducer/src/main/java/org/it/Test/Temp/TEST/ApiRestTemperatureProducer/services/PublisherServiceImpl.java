package org.it.Test.Temp.TEST.ApiRestTemperatureProducer.services;

import org.it.Test.Temp.TEST.ApiRestTemperatureProducer.v1.requestModels.TemperatureRecordRequest;
import com.google.gson.Gson;
import org.it.Test.Temp.TEST.ApiRestTemperatureProducer.dto.TemperatureRecordDTO;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class PublisherServiceImpl implements PublisherService{

    private final KafkaProducer<String,String> producer;

    private final Gson gson;


    public PublisherServiceImpl(KafkaProducer producer, Gson gson){
        this.producer = producer;
        this.gson = gson;
    }

    /**
     * Sets up the DTO record data and sends it to kafka
     * @param request
     */
    @Override
    public void publishTemperatureRecord(TemperatureRecordRequest request, String topic) {
        TemperatureRecordDTO dto = new TemperatureRecordDTO(request.getTemperature(), request.getSensorUuid());
        dto.setDate(LocalDate.now());
        dto.setTimestamp(LocalDateTime.now());
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, dto.getSensorUuid(), gson.toJson(dto));
        producer.send(producerRecord);
    }
}
