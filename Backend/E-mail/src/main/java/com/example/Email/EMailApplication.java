package com.example.Email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EMailApplication {

	public static void main(String[] args) {

		SpringApplication.run(EMailApplication.class, args);
	}

}
