package com.mitralabs.customer.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerEvent implements CustomerEvent {

	private String firstName;
	private String lastName;
	private String address;
	private String email;
}
