package com.mitralabs.account.eventhandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitralabs.account.service.AccountService;
import com.mitralabs.customer.event.CustomerCreatedEvent;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;

@EventSubscriber(id = "accountEventHandlers")
@Slf4j
public class AccountEventHandler {

	@Autowired
	private AccountService accountService;

	@EventHandlerMethod
	public void customerCreatedEvent(DispatchedEvent<CustomerCreatedEvent> event) {
		log.info("CustomerCreatedEvent recived", event.getEntityId());

		CustomerCreatedEvent cEvent = event.getEvent();

		try {
			accountService.createAccount(event.getEntityId(), "Test", cEvent.getAccountType(), "Test");
		} catch (Exception e) {

			e.printStackTrace();
			log.error("Error creating account", e.getMessage());
		}
	}
}
