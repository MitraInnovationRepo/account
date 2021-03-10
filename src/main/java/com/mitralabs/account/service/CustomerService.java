package com.mitralabs.customer.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableMap;
import com.mitralabs.customer.CustomerAggregate;
import com.mitralabs.customer.command.CreateCustomerCommand;
import com.mitralabs.customer.command.CustomerCommand;
import com.mitralabs.customer.dao.QueryDao;
import com.mitralabs.customer.dto.CustomerDTO;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithMetadata;
import io.eventuate.SaveOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerService {

	private final AggregateRepository<CustomerAggregate, CustomerCommand> aggregateRepository;

	@Autowired
	private QueryDao queryDao;

	public CustomerService(AggregateRepository<CustomerAggregate, CustomerCommand> aggregateRepository) {
		this.aggregateRepository = aggregateRepository;
	}

	public String createCustomer(CustomerDTO customerDTO) throws Exception {

		try {
			String aggregateId = aggregateRepository
					.save(new CreateCustomerCommand(customerDTO.getFirstName(), customerDTO.getLastName(),
							customerDTO.getAddress(), customerDTO.getEmail()),
							Optional.of(new SaveOptions().withEventMetadata(
									ImmutableMap.of("eventTime", String.valueOf(new Date().getTime())))))
					.get().getEntityId();

			log.info("customer [{}] added", aggregateId);

			queryDao.insertCustomer(aggregateId, customerDTO.getFirstName(), customerDTO.getLastName(),
					customerDTO.getAddress(), customerDTO.getEmail());

			return aggregateId;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public CustomerDTO getCustomer(String aggregateId) throws Exception {

		try {
			EntityWithMetadata<CustomerAggregate> customerAggregate = aggregateRepository.find(aggregateId).get();
			return customerAggregate.getEntity().getCustomer();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}