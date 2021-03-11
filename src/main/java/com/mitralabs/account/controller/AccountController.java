package com.mitralabs.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mitralabs.account.dto.AccountDTO;
import com.mitralabs.account.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/account/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable String id) {

		AccountDTO account = null;
		try {
			account = accountService.getAccount(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (account == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
	}
}
