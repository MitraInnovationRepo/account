package com.mitralabs.customer.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	@Size(min = 1, max = 30)
	private String firstName;

	@Size(min = 1, max = 30)
	private String lastName;

	@Size(min = 1, max = 50)
	private String address;

	@Size(min = 1, max = 30)
	private String email;

}
