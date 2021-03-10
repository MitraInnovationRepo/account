package com.mitralabs.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.mitralabs.customer.command.CustomerCommand;
import com.mitralabs.customer.service.CustomerService;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;

@Configuration
@Import({ TramJdbcKafkaConfiguration.class, DBConfig.class })
@EnableEventHandlers
public class BackendConfiguration {


	@Bean
	public AggregateRepository<CustomerAggregate, CustomerCommand> aggregateRepository(
			EventuateAggregateStore eventStore) {
		return new AggregateRepository<>(CustomerAggregate.class, eventStore);
	}

	@Bean
	public CustomerService customerService(
			AggregateRepository<CustomerAggregate, CustomerCommand> aggregateRepository) {
		return new CustomerService(aggregateRepository);
	}
}
