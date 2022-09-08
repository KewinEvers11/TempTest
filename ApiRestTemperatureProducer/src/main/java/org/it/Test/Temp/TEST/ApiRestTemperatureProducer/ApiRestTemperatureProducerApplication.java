package org.it.Test.Temp.TEST.ApiRestTemperatureProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan("org.it.Test.Temp.TEST.ApiRestTemperatureProduce")
public class ApiRestTemperatureProducerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(ApiRestTemperatureProducerApplication.class, args);
		System.out.println(context.getBean("kafkaProducer"));
	}

}
