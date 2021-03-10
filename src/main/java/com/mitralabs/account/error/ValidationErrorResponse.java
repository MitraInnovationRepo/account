package com.mitralabs.customer.error;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrorResponse {

	private List<Violation> violations = new ArrayList<>();

}
