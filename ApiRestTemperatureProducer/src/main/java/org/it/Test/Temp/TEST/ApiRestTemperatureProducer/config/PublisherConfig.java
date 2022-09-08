package org.it.Test.Temp.TEST.ApiRestTemperatureProducer.config;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PublisherConfig {

    private String host = "127.0.0.1";
    private String port = "9092";

    @Bean("kafkaProducer")
    public KafkaProducer getKafkaProducer() {
        String bootstrapServers = String.format("%s:%s", host, port);
        // Kafka consumer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // Kafka high throughput producer config
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32 * 1024));
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        // creating KafkaProducer
        KafkaProducer<String, String> producer= new KafkaProducer<String, String>(properties);
        return producer;
    }

    @Bean
    public Gson getGsonObject() {
        return new Gson();
    }


}
