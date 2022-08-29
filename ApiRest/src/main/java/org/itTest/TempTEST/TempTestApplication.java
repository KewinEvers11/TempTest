package org.itTest.TempTEST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validation;

@SpringBootApplication
public class TempTestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(TempTestApplication.class, args);
		LocalValidatorFactoryBean coso = (LocalValidatorFactoryBean) ctxt.getBean("getValidator");
		System.out.println(coso);
	}

}
