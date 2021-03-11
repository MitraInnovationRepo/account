package com.mitralabs.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.mitralabs.account.command.AccountCommand;
import com.mitralabs.account.service.AccountService;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;

@Configuration
@Import({ TramJdbcKafkaConfiguration.class, DBConfig.class })
@EnableEventHandlers
public class BackendConfiguration {

	@Bean
	public AggregateRepository<AccountAggregate, AccountCommand> aggregateRepository(
			EventuateAggregateStore eventStore) {
		return new AggregateRepository<>(AccountAggregate.class, eventStore);
	}

	@Bean
	public AccountService customerService(AggregateRepository<AccountAggregate, AccountCommand> aggregateRepository) {
		return new AccountService(aggregateRepository);
	}
}
