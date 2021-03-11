package com.mitralabs.account.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableMap;
import com.mitralabs.account.AccountAggregate;
import com.mitralabs.account.command.AccountCommand;
import com.mitralabs.account.command.AccountCreatedCommand;
import com.mitralabs.account.dao.QueryDao;
import com.mitralabs.account.dto.AccountDTO;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithMetadata;
import io.eventuate.SaveOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountService {

	private final AggregateRepository<AccountAggregate, AccountCommand> aggregateRepository;

	@Autowired
	private QueryDao queryDao;

	public AccountService(AggregateRepository<AccountAggregate, AccountCommand> aggregateRepository) {
		this.aggregateRepository = aggregateRepository;
	}

	public String createAccount(AccountDTO accountDTO) throws Exception {

		try {
			String aggregateId = aggregateRepository
					.save(new AccountCreatedCommand("SAVINGS"),
							Optional.of(new SaveOptions().withEventMetadata(
									ImmutableMap.of("eventTime", String.valueOf(new Date().getTime())))))
					.get().getEntityId();

			log.info("customer [{}] added", aggregateId);

//			queryDao.insertAccount(aggregateId, customerDTO.getFirstName(), customerDTO.getLastName(),
//					customerDTO.getAddress(), customerDTO.getEmail());

			return aggregateId;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public AccountDTO getAccount(String aggregateId) throws Exception {

		try {
			EntityWithMetadata<AccountAggregate> customerAggregate = aggregateRepository.find(aggregateId).get();
			return customerAggregate.getEntity().getAccount();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}