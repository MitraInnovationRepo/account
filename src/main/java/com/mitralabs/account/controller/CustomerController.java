package com.mitralabs.customer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitralabs.customer.dto.CustomerDTO;
import com.mitralabs.customer.error.ErrorResponse;
import com.mitralabs.customer.error.Response;
import com.mitralabs.customer.error.SuccessfulResponse;
import com.mitralabs.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String id) {

		CustomerDTO customer = null;
		try {
			customer = customerService.getCustomer(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
	}

	@PostMapping("/customer")
	public ResponseEntity<Response> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		String requestId = "-1";
		try {
			requestId = customerService.createCustomer(customerDTO);
		} catch (Exception e) {

			Response error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
			return new ResponseEntity<Response>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Response>(new SuccessfulResponse(requestId), HttpStatus.CREATED);
	}
}
