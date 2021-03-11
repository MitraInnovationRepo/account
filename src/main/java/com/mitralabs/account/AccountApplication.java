package com.mitralabs.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;

@SpringBootApplication
@Configuration
@Import({ EventuateDriverConfiguration.class, BackendConfiguration.class })
@EnableAutoConfiguration
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
