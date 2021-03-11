package com.mitralabs.account;

import java.util.List;

import com.mitralabs.account.command.AccountCommand;
import com.mitralabs.account.command.AccountCreatedCommand;
import com.mitralabs.account.dto.AccountDTO;
import com.mitralabs.account.event.AccountCreatedEvent;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;

public class AccountAggregate extends ReflectiveMutableCommandProcessingAggregate<AccountAggregate, AccountCommand> {

	@Getter
	private AccountDTO account;

	public List<Event> process(AccountCreatedCommand cmd) {
		return EventUtil.events(new AccountCreatedEvent(cmd.getAccountType()));
	}

	public void apply(AccountCreatedEvent event) {
		account = new AccountDTO();
		account.setAccountType(event.getAccountType());
	}
}
