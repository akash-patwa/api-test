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
}
