package io.apitest.example;

import io.apitest.example.enums.CustomerStatus;
import io.apitest.example.enums.FieldType;
import io.apitest.example.model.*;
import io.apitest.example.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasantabiswas on 27/06/18.
 */

@SpringBootApplication
public class CustomerApp {


	private static final Logger logger = LoggerFactory.getLogger(CustomerApp.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerApp.class, args);
	}

	@Bean
	public CommandLineRunner setupCustomer(CustomerRepository customerRepository) {
		return (args) -> {
			customerRepository.save(new Customer("User 1","Kolkata, West Bengal",false, CustomerStatus.INACTIVE,1,1));
			customerRepository.save(new Customer("User 2","Bangalore, Karnataka",false, CustomerStatus.ACTIVE,2,2));
			customerRepository.save(new Customer("User 3","Mumbai, Maharastra",true, CustomerStatus.BLOCKED,3,3));
			customerRepository.save(new Customer("User 4","Chennai, Tamilnadu",false, CustomerStatus.ACTIVE,4,4));
			logger.info("The sample customer data has been generated");
		};
	}

	@Bean
	public CommandLineRunner setupField(FieldRepository fieldRepository) {
		return (args) -> {
			fieldRepository.save(new Field("Name1",FieldType.STRING));
			fieldRepository.save(new Field("Address1",FieldType.STRING));
			fieldRepository.save(new Field("Is Onboarded1",FieldType.BOOLEAN));
			fieldRepository.save(new Field("Status1",FieldType.INTEGER));
			fieldRepository.save(new Field("Name2",FieldType.STRING));
			fieldRepository.save(new Field("Address2",FieldType.STRING));
			fieldRepository.save(new Field("Is Onboarded2",FieldType.BOOLEAN));
			fieldRepository.save(new Field("Name3",FieldType.STRING));
			fieldRepository.save(new Field("Address3",FieldType.STRING));
			fieldRepository.save(new Field("Status3",FieldType.INTEGER));
			fieldRepository.save(new Field("Name4",FieldType.STRING));
			fieldRepository.save(new Field("Address4",FieldType.STRING));
			fieldRepository.save(new Field("Is Onboarded4",FieldType.STRING));
			fieldRepository.save(new Field("Status14",FieldType.STRING));
			fieldRepository.save(new Field("Name5",FieldType.STRING));
			fieldRepository.save(new Field("Address5",FieldType.STRING));
			fieldRepository.save(new Field("Status5",FieldType.STRING));
			logger.info("The sample field data have been generated");
		};
	}
}
