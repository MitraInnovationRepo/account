package com.mitralabs.customer.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCustomerCommand implements CustomerCommand {

	private String firstName;
	private String lastName;
	private String address;
	private String email;
}
